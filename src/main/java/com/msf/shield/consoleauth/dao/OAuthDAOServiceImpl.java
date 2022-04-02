package com.msf.shield.consoleauth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.msf.shield.consoleauth.model.MenuPermissions;
import com.msf.shield.consoleauth.model.UserEntity;

@Repository
public class OAuthDAOServiceImpl implements OAuthDAOService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public UserEntity getUserDetails(String username) {

		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

		System.out.println("inside getUserDetails");

		UserEntity userEntity = jdbcTemplate.queryForObject(
				"SELECT * FROM shield_admin_console.USER_LIST WHERE login_id=?", new String[] { username },
				(ResultSet rs, int rowNum) -> {
					UserEntity user = new UserEntity();
					user.setUsername(rs.getString("login_id"));
					user.setId(rs.getString("login_id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
					return user;
				});

		if (userEntity != null) {

			List<MenuPermissions> menuList = new ArrayList<MenuPermissions>();
			final String procedureCall = "{call get_menu_list(?,?)}";
			Connection connection = null;

			try {

				connection = jdbcTemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, userEntity.getUsername());

				boolean results = callableSt.execute();

				while (results) {

					ResultSet rs = callableSt.getResultSet();

					while (rs.next()) {
						menuList.add(toMenuPermissions(rs));
					}

					rs.close();
					results = callableSt.getMoreResults();
				}

				if (menuList != null && !menuList.isEmpty()) {
					for (MenuPermissions permission : menuList) {
						GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getMenuid());
						grantedAuthoritiesList.add(grantedAuthority);
					}

					userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);
					userEntity.setMeuList(menuList);
				}

			} catch (SQLException e) {

				e.printStackTrace();

			} finally {
				if (connection != null)
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}

		return userEntity;
	}

	private MenuPermissions toMenuPermissions(ResultSet result) throws SQLException {

		MenuPermissions menuPerm = new MenuPermissions();
		menuPerm.setMenuid(result.getString("id"));
		menuPerm.setMenuName(result.getString("name"));
		menuPerm.setParentMenu(result.getString("parent_menu"));
		menuPerm.setStatus(result.getString("status"));

		return menuPerm;

	}

}

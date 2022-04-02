package com.msf.shield.consoleauth.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserEntity {

	private String id;
	private String name;
	private String username;
	private String password;
	private String role;
	private Collection<MenuPermissions> meuList = new ArrayList<>();
	private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<GrantedAuthority> getGrantedAuthoritiesList() {
		return grantedAuthoritiesList;
	}

	public void setGrantedAuthoritiesList(Collection<GrantedAuthority> grantedAuthoritiesList) {
		this.grantedAuthoritiesList = grantedAuthoritiesList;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<MenuPermissions> getMeuList() {
		return meuList;
	}

	public void setMeuList(Collection<MenuPermissions> meuList) {
		this.meuList = meuList;
	}
}

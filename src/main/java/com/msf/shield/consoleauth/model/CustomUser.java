package com.msf.shield.consoleauth.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Collection<MenuPermissions> meuList = new ArrayList<>();

	public CustomUser(UserEntity userEntity) {
		super(userEntity.getUsername(), userEntity.getPassword(), userEntity.getGrantedAuthoritiesList());
		this.id = userEntity.getId();
		this.name = userEntity.getName();
		this.meuList = userEntity.getMeuList();
	}

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

	public Collection<MenuPermissions> getMeuList() {
		return meuList;
	}

	public void setMeuList(Collection<MenuPermissions> meuList) {
		this.meuList = meuList;
	}
}

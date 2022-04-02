package com.msf.shield.consoleauth.dao;

import com.msf.shield.consoleauth.model.UserEntity;

public interface OAuthDAOService {

	public UserEntity getUserDetails(String username);
}

package com.mcorvera.springsecuritydb.service;

import java.util.List;
import java.util.Optional;

import com.mcorvera.springsecuritydb.model.User;
import com.mcorvera.springsecuritydb.model.security.AuthenticatedUser;

public interface HomeServiceI {
	
	public List<User> getAllListUser();

	public Optional<AuthenticatedUser> getAuthenticatedUser();

}

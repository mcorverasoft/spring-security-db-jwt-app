package com.mcorvera.springsecuritydb.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;

import com.mcorvera.springsecuritydb.beans.AuthenticationResponse;
import com.mcorvera.springsecuritydb.beans.SignUpRequest;
import com.mcorvera.springsecuritydb.model.security.AuthenticatedUser;

public interface AuthenticationServiceI {

	public AuthenticationResponse authenticateUser(String usernameOrEmail, String password) throws AuthenticationCredentialsNotFoundException, AuthenticationException ;
	
	public AuthenticationResponse registerUser(SignUpRequest signup);
	
	public Optional<AuthenticatedUser> getAuthenticatedUser(); //Get Profile of user
	
}

package com.mcorvera.springsecuritydb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mcorvera.springsecuritydb.model.User;
import com.mcorvera.springsecuritydb.model.security.AuthenticatedUser;
import com.mcorvera.springsecuritydb.repository.UserRepository;

@Service
public class HomeService implements HomeServiceI {
	@Autowired
	private UserRepository userRepistory;

	@Override
	public List<User> getAllListUser() {
		// TODO Auto-generated method stub
		return userRepistory.findAll();
	}

	@Override
	public Optional<AuthenticatedUser> getAuthenticatedUser() {
		AuthenticatedUser user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
        	user=(AuthenticatedUser) authentication.getPrincipal();
        	return Optional.of(user);
        }
        else 
        	return Optional.empty();
	}
	
	
	
}

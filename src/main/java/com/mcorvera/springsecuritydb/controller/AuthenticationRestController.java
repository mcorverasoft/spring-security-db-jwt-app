package com.mcorvera.springsecuritydb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mcorvera.springsecuritydb.beans.AuthenticationResponse;
import com.mcorvera.springsecuritydb.beans.LoginRequest;
import com.mcorvera.springsecuritydb.beans.SignUpRequest;
import com.mcorvera.springsecuritydb.service.AuthenticationServiceI;

@RestController
@RequestMapping("/app")
public class AuthenticationRestController {
	
	@Autowired
	private AuthenticationServiceI authenticationServicei; 
	
	
	@PostMapping("/login")
	public AuthenticationResponse authenticateUser(@Valid @RequestBody LoginRequest login) {
		AuthenticationResponse authenticationResponse=new AuthenticationResponse();
		try {
			authenticationResponse=authenticationServicei.authenticateUser(login.getUsernameOrEmail(), login.getPassword());
		}catch( AuthenticationCredentialsNotFoundException ex){
			authenticationResponse.setError(ex.getMessage());
		}
		return authenticationResponse;
	}
	
	
	@PostMapping("/signup")
	public AuthenticationResponse authenticateUser(@Valid @RequestBody SignUpRequest signup) {
		AuthenticationResponse authenticationResponse=new AuthenticationResponse();
		try {
			authenticationResponse=authenticationServicei.registerUser(signup);
		}catch(Exception ex){
			authenticationResponse.setError(ex.getMessage());
		}
		return authenticationResponse;
	}

}

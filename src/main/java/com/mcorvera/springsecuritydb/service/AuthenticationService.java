package com.mcorvera.springsecuritydb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mcorvera.springsecuritydb.beans.AuthenticationResponse;
import com.mcorvera.springsecuritydb.beans.SignUpRequest;
import com.mcorvera.springsecuritydb.model.Role;
import com.mcorvera.springsecuritydb.model.RoleName;
import com.mcorvera.springsecuritydb.model.User;
import com.mcorvera.springsecuritydb.model.security.AuthenticatedUser;
import com.mcorvera.springsecuritydb.repository.RoleRepository;
import com.mcorvera.springsecuritydb.repository.UserRepository;
import com.mcorvera.springsecuritydb.security.JwtTokenProvider;

@Service
public class AuthenticationService implements AuthenticationServiceI {
	
	private static final Logger logger=LoggerFactory.getLogger(AuthenticationService.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Value("${app.jwtType}")
	private String jwtType;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	
	@Override
	public AuthenticationResponse authenticateUser(String usernameOrEmail, String password) throws AuthenticationCredentialsNotFoundException, AuthenticationException  {
		// TODO Auto-generated method stub
		AuthenticationResponse authenticationResponse =new AuthenticationResponse();
		try {
			Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//Generate the json web token to user:
			String jwtToken=jwtTokenProvider.generateJwtToken((AuthenticatedUser) authentication.getPrincipal());	
			authenticationResponse.setToken(jwtToken);
			authenticationResponse.setTokenType(jwtType);
			authenticationResponse.setAuthenticated(true);
		}
		catch(AuthenticationCredentialsNotFoundException ex) {
			logger.error("Credentials not valid: "+ ex.getMessage());
			throw ex;
			
		}
		catch(AuthenticationException ex) {
			logger.error("Credentials not valid: "+ ex.getMessage());
			throw ex;
		}
		
		return authenticationResponse;
	}

	@Override
	public AuthenticationResponse registerUser(SignUpRequest signup) {
		// TODO Auto-generated method stub
		
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		
		try {
		
			if(userRepository.existsByEmail(signup.getEmail())) {
				authenticationResponse.setCreated(false);
				authenticationResponse.setError("the email already exist.");
				return authenticationResponse;
			}
			
			if(userRepository.existsByUsername(signup.getUsername())) {
				authenticationResponse.setCreated(false);
				authenticationResponse.setError("the username already exist.");
				return authenticationResponse;
			}
			
			Role role=roleRepository.findByName(RoleName.ROL_USER);
			if(role==null) {
				authenticationResponse.setCreated(false);
				authenticationResponse.setError("the rol ROL_USER is not registered");
				return authenticationResponse;
			}
			List<Role> roles = new ArrayList<>();
			roles.add(role);
			
			User user =new User(signup.getEmail(), signup.getUsername(), signup.getName(), bcrypt.encode(signup.getPassword()), roles);
			userRepository.save(user);
			
			Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signup.getEmail(), signup.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//Generate the json web token to user:
			String jwtToken=jwtTokenProvider.generateJwtToken((AuthenticatedUser) authentication.getPrincipal());	
			authenticationResponse.setToken(jwtToken);
			authenticationResponse.setTokenType(jwtType);
			authenticationResponse.setAuthenticated(true);
			authenticationResponse.setCreated(true);
			authenticationResponse.setError("");
		}catch(Exception ex) {
			logger.error("Ha ocurrido un errror al crear el usuario: "+ ex.getMessage());
			throw ex;
		}
		
		return authenticationResponse;
	}

	@Override
	public Optional<AuthenticatedUser> getAuthenticatedUser() {
		// TODO Auto-generated method stub
		return null;
	}

}

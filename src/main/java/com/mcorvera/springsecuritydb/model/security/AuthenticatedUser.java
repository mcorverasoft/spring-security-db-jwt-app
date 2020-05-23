package com.mcorvera.springsecuritydb.model.security;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcorvera.springsecuritydb.model.User;
import com.mcorvera.springsecuritydb.model.audit.Audit;


public class AuthenticatedUser extends Audit<Long> implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7478279946802298801L;


	private Long id;
	
	
	private String email;

	private String name;
	
	private String lastname;
	
	private String username;

	@JsonIgnore
	private String password;
	
	private String phone;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	private boolean isAccountNonExpired;
	
	private boolean isAccountNonLocked;

	private boolean isCredentialsNonExpired;
	
	private boolean isEnabled;

	public AuthenticatedUser(Long id, String email, String name, String username, String lastname, String password,
			String phone,
			Collection<? extends GrantedAuthority> authorities, boolean isAccountNonExpired, boolean isAccountNonLocked,
			boolean isCredentialsNonExpired, boolean isEnabled, 
			Instant createdAt, Long createdBy, Long lastModifiedBy, Instant updatedAt) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.username = username;
		this.lastname = lastname;
		this.password = password;
		this.phone=phone;
		this.authorities = authorities;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.setCreatedAt(createdAt);
		this.setCreatedBy(createdBy);
		this.setLastModifiedBy(lastModifiedBy);
		this.setUpdatedAt(updatedAt);
	}
	
	
	
	public static AuthenticatedUser getUserAuthenticated(User user) {
	
		/*Add Roles with .forEach
		 * List<GrantedAuthority> authorities =new ArrayList<>();	 	
		user.getRoles().forEach(role-> autoauthorities.add(new SimpleGrantedAuthority(role.getName().name())));
		*/
		
		
		/*
		 * Add Roles with stream().map(role->to do)
		 * */
		
		List<GrantedAuthority> authorities=user.getRoles().stream().map(role-> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		
		
		return new AuthenticatedUser(user.getId(), user.getEmail(), user.getName(), user.getUsername(), user.getLastname(),
				user.getPassword(), user.getPhone(),
				authorities, user.getIsaccountnonexpired(), user.getIsaccountnonlocked(), user.getIscredentialsnonexpired(),
				user.getIsenabled(),
				user.getCreatedAt(), user.getCreatedBy(), user.getLastModifiedBy(), user.getUpdatedAt());
			}

	public Long getId() {
		return id;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}


}

package com.mcorvera.springsecuritydb.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcorvera.springsecuritydb.model.audit.Audit;

@Entity
@Table(name="users",uniqueConstraints= {
								@UniqueConstraint(columnNames={"email"}),
								@UniqueConstraint(columnNames={"username"})})
public class User extends Audit<Long> {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Email
	@Column(nullable = false)
	private String email;
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@NotBlank
	@Column(nullable = false)
	private String username;
	
	@NotBlank
	@JsonIgnore
	@Column(nullable=false)
	private String password;
	
	@Column(nullable = false, name= "isaccountnonexpired")
	private Boolean isaccountnonexpired=true;
	
	@Column(nullable = false, name= "isaccountnonlocked")
	private Boolean isaccountnonlocked=true;
	
	@Column(nullable = false, name = "iscredentialsnonexpired")
	private Boolean iscredentialsnonexpired=true;

	@Column(nullable = false, name="isenabled")
	private Boolean isenabled=true;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
				joinColumns = @JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name="role_id"))
	private List<Role> roles =new ArrayList<>();
	
	public User() {
		super();
	}
	
	public User(@NotBlank @Email String email, @NotBlank String username, @NotBlank String name, @NotBlank String password, List<Role> roles) {
		super();
		this.email = email;
		this.username = username;
		this.name = name;
		this.password = password;
		this.roles=roles;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Boolean getIsaccountnonexpired() {
		return isaccountnonexpired;
	}
	public void setIsaccountnonexpired(Boolean isaccountnonexpired) {
		this.isaccountnonexpired = isaccountnonexpired;
	}
	public Boolean getIsaccountnonlocked() {
		return isaccountnonlocked;
	}
	public void setIsaccountnonlocked(Boolean isaccountnonlocked) {
		this.isaccountnonlocked = isaccountnonlocked;
	}
	public Boolean getIscredentialsnonexpired() {
		return iscredentialsnonexpired;
	}
	public void setIscredentialsnonexpired(Boolean iscredentialsnonexpired) {
		this.iscredentialsnonexpired = iscredentialsnonexpired;
	}
	public Boolean getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(Boolean isenabled) {
		this.isenabled = isenabled;
	}
	@Override
	public void setCreatedBy(Long createdBy) {
		// TODO Auto-generated method stub
		if(createdBy==0)
			createdBy=this.id;
		super.setCreatedBy(createdBy);
	}
	@Override
	public void setLastModifiedBy(Long lastModifiedBy) {
		// TODO Auto-generated method stub
		if(lastModifiedBy==0)
			lastModifiedBy=this.id;
		super.setLastModifiedBy(lastModifiedBy);
	}
	
}

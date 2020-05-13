package com.mcorvera.springsecuritydb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcorvera.springsecuritydb.model.Role;
import com.mcorvera.springsecuritydb.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public List<Role> findAll();
	public Role findByName(RoleName rolename );

}

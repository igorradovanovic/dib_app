package com.dib.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dib.model.Role;
import com.dib.sys.CustomRepository;

@Repository
public interface RoleRepository extends CustomRepository<Role, Long> {
	
	List<Role> findByRolNameContainingIgnoreCase(String rolName);

}

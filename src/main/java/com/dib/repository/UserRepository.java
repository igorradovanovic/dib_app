package com.dib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dib.model.Role;
import com.dib.model.User;
import com.dib.sys.CustomRepository;

@Repository
public interface UserRepository extends CustomRepository<User, Long> {
	 
	User findOneByUserName(String name);
	
	@Query("SELECT r from UserRoles ur INNER JOIN ur.role r INNER JOIN ur.user u WHERE u.userName = :usrName")
    List<Role> findAuthorities(@Param("usrName") String username);
	
}

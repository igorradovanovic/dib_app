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
	 
	User findByUserName(String name);
	
	User findByUserEmail(String email);
	
	User findByUserNameAndUserIdNot(String name, Long id);

	User findByUserEmailAndUserIdNot(String email, Long id);
	
	User findOneByUserName(String name);
	
	@Query("SELECT r from UserRoles ur INNER JOIN ur.role r INNER JOIN ur.user u WHERE u.userName = :usrName")
    List<Role> findAuthorities(@Param("usrName") String username);
	
	@Query(value = "SELECT roles.rol_name FROM user_roles\r\n" + 
			"Inner join roles\r\n" + 
			"on  user_roles.urr_rol_id = roles.rol_id \r\n" + 
			"where user_roles.urr_usr_id = :usrId", nativeQuery = true)
	String getUserRole(@Param("usrId") Long usrId);
}

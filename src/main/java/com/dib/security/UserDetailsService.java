package com.dib.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.dib.model.Role;
import com.dib.model.User;
import com.dib.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) {
		LOGGER.info("Authenticating {}", login);
		String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
		User userFromDatabase = userRepository.findOneByUserName(lowercaseLogin);

		List<Role> roles = userRepository.findAuthorities(lowercaseLogin);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			GrantedAuthority a = new SimpleGrantedAuthority("ROLE_" + role.getRolName());
			authorities.add(a);
		}

		if (userFromDatabase != null && userFromDatabase.getUserEnabled() != null
				&& userFromDatabase.getUserEnabled() == true) {
			return new org.springframework.security.core.userdetails.User(lowercaseLogin,
					userFromDatabase.getUserPassword(), authorities);
		} else {
			throw new UsernameNotFoundException("User " + login + " was not found in the " + "database");
		}
	}
}

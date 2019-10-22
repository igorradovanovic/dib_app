package com.dib.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class CustomUserDetails extends User {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long accountID;
	
	

	
	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Long accountID) {
		super(username, password, authorities);
		this.accountID = accountID;
	}


	public Long getAccountID() {
		return accountID;
	}
	
	
}

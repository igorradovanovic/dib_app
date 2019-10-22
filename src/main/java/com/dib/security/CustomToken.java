package com.dib.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomToken extends UsernamePasswordAuthenticationToken {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		
	}
	
    public CustomToken(Object principal, Object credentials) {
    	super(principal, credentials);
    	
	}
	
	
}

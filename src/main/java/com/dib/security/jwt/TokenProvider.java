package com.dib.security.jwt;

import java.io.UnsupportedEncodingException;
//import java.util.*;
//import java.util.stream.Collectors;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import com.dib.security.CustomToken;

@Component
public class TokenProvider {

	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

	private static final String AUTHORITIES_KEY = "auth";

	private String secretKey;

	private long tokenValidityInSeconds;

	private long tokenValidityInSecondsForRememberMe;

	
	@PostConstruct
	public void init() {
		// this.secretKey = "sampleProjectCyrilic";
		this.secretKey = TextCodec.BASE64.encode("baseproject");
		this.tokenValidityInSeconds = 60000 * 600;
		this.tokenValidityInSecondsForRememberMe = 60000 * 600;
		
	}

	public String createToken(Authentication authentication, Boolean rememberMe, Long oruId) throws Exception {
		List<GrantedAuthority> authorities1 = (List<GrantedAuthority>) authentication.getAuthorities();
		String authorities = authentication.getAuthorities().stream().map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));

		long now = (new Date()).getTime();
		Date validity;
		if (rememberMe) {
			validity = new Date(now + this.tokenValidityInSecondsForRememberMe);
		} else {
			validity = new Date(now + this.tokenValidityInSeconds);
		}
		System.out.println("CREATE TOKEN " + validity);
		return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities).claim("oruId", oruId)
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes("UTF-8")).setExpiration(validity).compact();
	}

	public Authentication getAuthentication(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8")).parseClaimsJws(token).getBody();

		List<String> auths = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(","));

		List<GrantedAuthority> l = new ArrayList<GrantedAuthority>();
		for (int i = 0; i < auths.size(); i++) {
			l.add(new SimpleGrantedAuthority(auths.get(i)));
		}

		Collection<? extends GrantedAuthority> authorities = l;
		
		Long oruId = new Long((Integer) claims.get("oruId"));

		User principal = new User(claims.getSubject(), "", authorities);

		return new CustomToken(principal, "", authorities);
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8")).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedEncodingException e) {
			log.info("Invalid JWT signature: " + e.getMessage());
			return false;
		}
	}
	
	public static void main(String[] args) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		String secretKey = TextCodec.BASE64.encode("baseproject");
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOIiwiZXhwIjoxNTIwNDIzMTI3fQ.lvYH4uy0jk52C71jaKKCTBRljJwhuMgCU4b1ITTN8PKDgDcoAKGGYJPU8Z7CQcEG0yErYuWfftfOBPDvxostAg";
		Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8")).parseClaimsJws(token).getBody();
	}

	

}
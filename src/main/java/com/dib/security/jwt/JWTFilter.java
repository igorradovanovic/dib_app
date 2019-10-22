package com.dib.security.jwt;
import java.io.IOException;

import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * Filters incoming requests and installs a Spring Security principal if a
 * header corresponding to a valid user is found.
 */
public class JWTFilter extends GenericFilterBean {

	private final Logger log = LoggerFactory.getLogger(JWTFilter.class);

	static final String ORIGIN = "ORIGIN";

	private TokenProvider tokenProvider;

	public JWTFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		try {

			System.out.println("************** JWT FILTER START " + new Date());
			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
			HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
			String jwt = resolveToken(httpServletRequest);

			if (StringUtils.hasText(jwt)) {
				if (this.tokenProvider.validateToken(jwt)) {
					Authentication authentication = this.tokenProvider.getAuthentication(jwt);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (ExpiredJwtException eje) {
			System.out.println("************** UNAUTHORIZED");
			log.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
			((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private String resolveToken(HttpServletRequest request) {

		String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
		// System.out.println("BEARER TOKEN " + bearerToken);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			String jwt = bearerToken.substring(7, bearerToken.length());
			return jwt;
		}
		return null;
	}

	private void handleOriginMethod(HttpServletRequest request, HttpServletResponse servletResponse)
			throws IOException {
		String origin = request.getHeader("origin");
		servletResponse.addHeader("Access-Control-Allow-Origin", origin);
		servletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		servletResponse.addHeader("Access-Control-Allow-Credentials", "true");
		servletResponse.addHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		// }
		if (request.getMethod().equals("OPTIONS")) {
			servletResponse.getWriter().print("OK");
			servletResponse.getWriter().flush();
			return;
		}
	}
}

package com.dib.config;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.dib.security.AuthoritiesConstants;
import com.dib.security.Http401UnauthorizedEntryPoint;
import com.dib.security.jwt.JWTConfigurer;
import com.dib.security.jwt.TokenProvider;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	   @Override
//	    protected void configure(HttpSecurity httpSecurity) throws Exception {
//	        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
//	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private Http401UnauthorizedEntryPoint authenticationEntryPoint;

	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		http
		  .exceptionHandling()
		  .authenticationEntryPoint(authenticationEntryPoint) .and() .csrf()
		  .disable() .headers() .frameOptions() .disable() .and()
		  .sessionManagement()
		  .sessionCreationPolicy(SessionCreationPolicy.STATELESS) .and()
		  .httpBasic() .and() .authorizeRequests()
		  .antMatchers("/config/**").hasAuthority(AuthoritiesConstants.ADMIN)
		  .antMatchers("/api/auth").permitAll()
		  // in real env this must be peremited forgot/reset controller toDo
		  .antMatchers("/api/forgot").permitAll()
		  .antMatchers("/api/reset").permitAll()
		  
		  .antMatchers("/api/**").authenticated()
		  .antMatchers("/greeting").permitAll()
		  

		  .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.
		  ADMIN) .antMatchers("/assets/**").permitAll() //
		  .antMatchers("/*").permitAll() 
		  //to enable swagger testing, security
		  .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").hasAuthority(AuthoritiesConstants.ADMIN)
		  .anyRequest().authenticated() .and()
		  .apply(securityConfigurerAdapter());
		 
	}

	@Bean
    public AuthenticationManager authenticationManager() {
        try {
            return authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		System.out.println(b.encode("password0511"));
	}


}
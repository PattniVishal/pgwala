package com.pgwala.buyerService.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			
			.csrf().disable()
		
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			
			.addFilterAfter(new JwtTokenVerifierFilter(), UsernamePasswordAuthenticationFilter.class)
		
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/v1/buyers").permitAll()
			//.antMatchers(HttpMethod.POST, "/auth").permitAll()
		
			.anyRequest().authenticated();
	}
	
}

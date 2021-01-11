package com.pgwala.authService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import com.pgwala.authService.jwt.JwtAuthenticationFilter;
import com.pgwala.authService.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
//			
//			.headers()
//			.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
//			.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "*"))
//			.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "*"))
//			.and()
		
			.csrf().disable()
			
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			
//			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
			
			.authorizeRequests()
			.antMatchers("/**").permitAll()
			
			.anyRequest().authenticated();
			
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

package com.pgwala.authService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
//		return new NoOpPasswordEncoder(); 
		return new BCryptPasswordEncoder();
	}
	
}

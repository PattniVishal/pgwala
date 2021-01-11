package com.pgwala.authService.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pgwala.authService.dao.UserDAO;
import com.pgwala.authService.entity.UserEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = userDAO.findByEmail(username);
		if(userEntity.isPresent()) {
			String email = userEntity.get().getEmail();
			String password = userEntity.get().getPassword();
			String encPass = encoder.encode(password);
			String role = userEntity.get().getRole();
			List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(role));
			return new User(email, encPass, authorities);
		}
		else {
			throw new UsernameNotFoundException("user with username " + username + " not found.");
		}
	}
	
}

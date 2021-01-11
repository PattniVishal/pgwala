package com.pgwala.authService.service;

import org.springframework.stereotype.Service;

import com.pgwala.authService.dto.RegisterDTO;
import com.pgwala.authService.dto.UserDTO;

@Service
public interface AuthService {

	public UserDTO getUser(String email);
	
	public UserDTO registerUser(RegisterDTO registerDTO);
	
}

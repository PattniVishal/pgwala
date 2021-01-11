package com.pgwala.authService.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {

	String email;
	String password;
	String role;
	String jwt;
	Date expiresAt;
	
}

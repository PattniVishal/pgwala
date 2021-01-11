package com.pgwala.authService.dto;

import lombok.Data;

@Data
public class RegisterDTO {

	String name;
	String email;
	String password;
	String role;
	String aadharNumber;
	
}

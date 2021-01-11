package com.pgwala.authService.dto;

import java.util.List;

import com.pgwala.authService.model.Property;

import lombok.Data;

@Data
public class SellerDTO {

	String name;
	String email;
	String password;
	String role;
	String aadharNumber;
	List<Property> properties;
	
}

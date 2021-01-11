package com.pgwala.sellerService.dto;

import java.util.List;

import com.pgwala.sellerService.entity.Property;

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

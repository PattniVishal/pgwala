package com.pgwala.sellerService.entity;

import java.util.List;

import lombok.Data;

@Data
public class Property {

	String id;
	String sellerName;
	String sellerEmail;
	String propertyName;
	Address address;
	List<AccomodationType> accomodationTypes;
	List<Facility> facilities;
	List<String> genders;
	String description;
	
}

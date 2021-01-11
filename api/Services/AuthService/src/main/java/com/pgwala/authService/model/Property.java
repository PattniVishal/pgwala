package com.pgwala.authService.model;

import java.util.List;

import lombok.Data;

@Data
public class Property {

	String id;
	String propertyName;
	Address address;
	List<AccomodationType> accomodationTypes;
	List<Facility> facilities;
	List<String> genders;
	String description;
	
}

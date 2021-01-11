package com.pgwala.propertyService.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("propertyDB")
public class Property {
	
	@Id
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

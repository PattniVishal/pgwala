package com.pgwala.propertyService.entity;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("sellerDB")
public class SellerEntity {

	@Id
	ObjectId id;
	String name;
	String email;
	String password;
	String role;
	String aadharNumber;
	Date createdDate;
	List<Property> properties;
	
}

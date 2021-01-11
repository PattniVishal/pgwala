package com.pgwala.buyerService.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "buyerDB")
public class BuyerEntity {

	@Id
	ObjectId id;
	String name;
	String email;
	String password;
	String role;
	Date createdDate;
	
}

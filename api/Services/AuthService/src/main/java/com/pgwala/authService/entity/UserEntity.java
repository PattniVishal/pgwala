package com.pgwala.authService.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("userDB")
public class UserEntity {

	@Id
	public ObjectId _id;
	public String email;
	public String password;
	public String role;
	
}

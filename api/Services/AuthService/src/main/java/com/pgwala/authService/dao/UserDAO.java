package com.pgwala.authService.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pgwala.authService.entity.UserEntity;

@Repository
public interface UserDAO extends MongoRepository<UserEntity, String> {

	Optional<UserEntity> findByEmail(String email);
	
}

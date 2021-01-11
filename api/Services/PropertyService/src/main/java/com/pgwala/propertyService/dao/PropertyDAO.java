package com.pgwala.propertyService.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pgwala.propertyService.entity.Property;

@Repository
public interface PropertyDAO extends MongoRepository<Property, String> {
	
}

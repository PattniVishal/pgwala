package com.pgwala.propertyService.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pgwala.propertyService.entity.SellerEntity;

@Repository
public interface SellerDAO extends MongoRepository<SellerEntity, String> {

	SellerEntity findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	@Query(value = "{'properties.id' : ?0}")
	public SellerEntity findSellerByPropertyId(String propertyId);
}

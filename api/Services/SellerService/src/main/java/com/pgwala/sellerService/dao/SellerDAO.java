package com.pgwala.sellerService.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pgwala.sellerService.entity.SellerEntity;

@Repository
public interface SellerDAO extends MongoRepository<SellerEntity, String> {

	Optional<SellerEntity> findByEmail(String email);
	
}

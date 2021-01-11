package com.pgwala.buyerService.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pgwala.buyerService.entity.BuyerEntity;

@Repository
public interface BuyerDAO extends MongoRepository<BuyerEntity, String> {
	
	Optional<BuyerEntity> findByEmail(String email);
	
}

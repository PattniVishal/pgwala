package com.pgwala.buyerService.service;

import java.util.List;

import com.pgwala.buyerService.dto.BuyerDTO;
import com.pgwala.buyerService.exception.UserNotFoundException;

public interface BuyerService {
	
	public List<BuyerDTO> getAllBuyers();
	
	public BuyerDTO getBuyer(String email) throws UserNotFoundException;
	
	public BuyerDTO addBuyer(BuyerDTO buyerDTO);
	
	public BuyerDTO updateBuyer(String email, BuyerDTO buyerDTO) throws UserNotFoundException;
	
	public boolean deleteBuyer(String email) throws UserNotFoundException;

}

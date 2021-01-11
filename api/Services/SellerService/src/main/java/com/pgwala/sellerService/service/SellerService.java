package com.pgwala.sellerService.service;

import java.util.List;

import com.pgwala.sellerService.dto.SellerDTO;
import com.pgwala.sellerService.exception.UserNotFoundException;

public interface SellerService {
	
	public List<SellerDTO> getAllSellers();
	
	public SellerDTO getSeller(String email) throws UserNotFoundException;
	
	public SellerDTO addSeller(SellerDTO sellerDTO);
	
	public SellerDTO updateSeller(String email, SellerDTO sellerDTO) throws UserNotFoundException;
	
	public boolean deleteSeller(String email) throws UserNotFoundException;

}

package com.pgwala.buyerService.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgwala.buyerService.dao.BuyerDAO;
import com.pgwala.buyerService.dto.BuyerDTO;
import com.pgwala.buyerService.entity.BuyerEntity;
import com.pgwala.buyerService.exception.UserNotFoundException;
import com.pgwala.buyerService.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BuyerDAO buyerDAO;

	public List<BuyerDTO> getAllBuyers() {
		List<BuyerEntity> allBuyersEntity = buyerDAO.findAll();
		List<BuyerDTO> allBuyersDTO = allBuyersEntity.stream()
										.map(buyer -> modelMapper.map(buyer, BuyerDTO.class))
										.collect(Collectors.toList());
		return allBuyersDTO;
	}
	
	public BuyerDTO getBuyer(String email) throws UserNotFoundException {
		Optional<BuyerEntity> buyerEntity = buyerDAO.findByEmail(email);
		if(buyerEntity.isPresent()) {
			BuyerDTO buyerDTOResponse = modelMapper.map(buyerEntity.get(), BuyerDTO.class);
			return buyerDTOResponse;
		}
		else {
			throw new UserNotFoundException("user with username " + email + " not found.");
		}
	}
	
	public BuyerDTO addBuyer(BuyerDTO buyerDTO) {
		BuyerEntity buyer = modelMapper.map(buyerDTO, BuyerEntity.class);
		buyer.setCreatedDate(new Date());
		BuyerEntity buyerEntity = buyerDAO.save(buyer);
		BuyerDTO buyerDTOResponse = modelMapper.map(buyerEntity, BuyerDTO.class);
		return buyerDTOResponse;
	}
	
	public BuyerDTO updateBuyer(String email, BuyerDTO buyerDTO) throws UserNotFoundException {
		Optional<BuyerEntity> buyer = buyerDAO.findByEmail(email);
		if(buyer.isPresent()) {
			modelMapper.map(buyerDTO, buyer.get());
			BuyerEntity buyerEntity = buyerDAO.save(buyer.get());
			BuyerDTO buyerDTOResponse = modelMapper.map(buyerEntity, BuyerDTO.class); 
			return buyerDTOResponse;
		}
		else {
			throw new UserNotFoundException("user with username " + email + " not found.");
		}
	}
	
	public boolean deleteBuyer(String email) throws UserNotFoundException {
		Optional<BuyerEntity> buyer = buyerDAO.findByEmail(email);
		if(buyer.isPresent()) {
			buyerDAO.delete(buyer.get());
			return true;
		}
		else {
			throw new UserNotFoundException("user with username " + email + " not found.");
		}
	}
	
}

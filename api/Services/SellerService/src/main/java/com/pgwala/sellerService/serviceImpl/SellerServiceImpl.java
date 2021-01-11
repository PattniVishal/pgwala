package com.pgwala.sellerService.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgwala.sellerService.dao.SellerDAO;
import com.pgwala.sellerService.dto.SellerDTO;
import com.pgwala.sellerService.entity.SellerEntity;
import com.pgwala.sellerService.exception.UserNotFoundException;
import com.pgwala.sellerService.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	SellerDAO sellerDAO;
	
	@Override
	public List<SellerDTO> getAllSellers() {
		List<SellerEntity> allSellersEntity = sellerDAO.findAll();
		List<SellerDTO> allSellersDTO = allSellersEntity.stream()
										.map(seller -> modelMapper.map(seller, SellerDTO.class))
										.collect(Collectors.toList());
		return allSellersDTO;
	}

	@Override
	public SellerDTO getSeller(String email) throws UserNotFoundException {
		Optional<SellerEntity> sellerEntity = sellerDAO.findByEmail(email);
		if(sellerEntity.isPresent()) {
			SellerDTO sellerDTOResponse = modelMapper.map(sellerEntity.get(), SellerDTO.class);
			return sellerDTOResponse;
		}
		else {
			throw new UserNotFoundException("user with username " + email + " not found.");
		}
	}

	@Override
	public SellerDTO addSeller(SellerDTO sellerDTO) {
		SellerEntity seller = modelMapper.map(sellerDTO, SellerEntity.class);
		seller.setCreatedDate(new Date());
		SellerEntity sellerEntity = sellerDAO.save(seller);
		SellerDTO sellerDTOResponse = modelMapper.map(sellerEntity, SellerDTO.class);
		return sellerDTOResponse;
	}

	@Override
	public SellerDTO updateSeller(String email, SellerDTO sellerDTO) throws UserNotFoundException {
		Optional<SellerEntity> seller = sellerDAO.findByEmail(email);
		if(seller.isPresent()) {
			modelMapper.map(sellerDTO, seller.get());
			SellerEntity sellerEntity = sellerDAO.save(seller.get());
			SellerDTO sellerDTOResponse = modelMapper.map(sellerEntity, SellerDTO.class); 
			return sellerDTOResponse;
		}
		else {
			throw new UserNotFoundException("user with username " + email + " not found.");
		}
	}

	@Override
	public boolean deleteSeller(String email) throws UserNotFoundException {
		Optional<SellerEntity> seller = sellerDAO.findByEmail(email);
		if(seller.isPresent()) {
			sellerDAO.delete(seller.get());
			return true;
		}
		else {
			throw new UserNotFoundException("user with username " + email + " not found.");
		}
	}

}

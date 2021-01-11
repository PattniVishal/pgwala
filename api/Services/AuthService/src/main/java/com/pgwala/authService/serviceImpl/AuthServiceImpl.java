package com.pgwala.authService.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pgwala.authService.dao.UserDAO;
import com.pgwala.authService.dto.BuyerDTO;
import com.pgwala.authService.dto.RegisterDTO;
import com.pgwala.authService.dto.SellerDTO;
import com.pgwala.authService.dto.UserDTO;
import com.pgwala.authService.entity.UserEntity;
import com.pgwala.authService.model.Property;
import com.pgwala.authService.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	RestTemplate restTemplate;
	
	private String SAVE_SELLER_URL = "http://localhost:8081/gateway-service/seller-service/api/v1/sellers";
	private String SAVE_BUYER_URL = "http://localhost:8081/gateway-service/buyer-service/api/v1/buyers";
	
	public UserDTO getUser(String email) {
		Optional<UserEntity> userEntity = userDAO.findByEmail(email);
		if(userEntity.isPresent()) {
			UserDTO userDTO = modelMapper.map(userEntity.get(), UserDTO.class);
			return userDTO;
		}
		return null;
	}
	
	public UserDTO registerUser(RegisterDTO registerDTO) {
		UserEntity userEntity = modelMapper.map(registerDTO, UserEntity.class);
		UserEntity savedUserEntity = userDAO.save(userEntity);
		UserDTO userDTO = modelMapper.map(savedUserEntity, UserDTO.class);
		if(registerDTO.getRole().equals("seller")) {
			SellerDTO sellerDTO = modelMapper.map(registerDTO, SellerDTO.class);
			List<Property> properties = new ArrayList<>();
			sellerDTO.setProperties(properties);
			restTemplate.postForObject(SAVE_SELLER_URL, sellerDTO, SellerDTO.class);
		}
		if(registerDTO.getRole().equals("buyer")) {
			BuyerDTO buyerDTO = modelMapper.map(registerDTO, BuyerDTO.class);
			restTemplate.postForObject(SAVE_BUYER_URL, buyerDTO, BuyerDTO.class);
		}
		return userDTO;
	}

}

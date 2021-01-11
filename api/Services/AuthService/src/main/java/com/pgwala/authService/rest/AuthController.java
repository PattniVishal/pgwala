package com.pgwala.authService.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgwala.authService.dto.RegisterDTO;
import com.pgwala.authService.dto.ResponseDTO;
import com.pgwala.authService.dto.UserDTO;
import com.pgwala.authService.jwt.JwtUtil;
import com.pgwala.authService.service.AuthService;
import com.pgwala.authService.service.UserDetailsServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Authentication authenticate = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
			authenticationManager.authenticate(authenticate);
		}
		catch(Exception e) {
			responseDTO.setStatus(0);
			responseDTO.setMessage("Error in AuthService [login()] : " + e.getMessage());
			responseDTO.setData(null);
//			return responseDTO;
			return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());
		
		final String jwtToken = jwtUtil.generateToken(userDetails);
		final Date expiresAt = jwtUtil.extractExpiration(jwtToken);
		
		System.out.println("------------- getTime() : " + (expiresAt.getTime() - new Date().getTime()) / 1000);
		
		UserDTO user = authService.getUser(userDTO.getEmail());
		user.setJwt(jwtToken);
		user.setExpiresAt(expiresAt);
		user.setPassword(null);
		
		responseDTO.setStatus(1);
		responseDTO.setMessage("login successful.");
		responseDTO.setData(user);
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", "Bearer " + jwtToken);
		
//		return responseDTO;
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			UserDTO userDTO = authService.registerUser(registerDTO);
			
			final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());
			
			final String jwtToken = jwtUtil.generateToken(userDetails);
			final Date expiresAt = jwtUtil.extractExpiration(jwtToken);
			
			userDTO.setPassword(null);
			userDTO.setJwt(jwtToken);
			userDTO.setExpiresAt(expiresAt);
			
			responseDTO.setStatus(1);
			responseDTO.setMessage("register successful.");
			responseDTO.setData(userDTO);
			
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Authorization", "Bearer " + jwtToken);
			
			return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
			
		}
		catch(Exception e) {
			responseDTO.setStatus(0);
			responseDTO.setMessage("Error in AuthService [register()] : " + e.getMessage());
			responseDTO.setData(null);
			return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
		}
	}

}

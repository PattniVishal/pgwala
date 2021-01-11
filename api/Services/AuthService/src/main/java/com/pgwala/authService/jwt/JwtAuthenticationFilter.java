package com.pgwala.authService.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgwala.authService.model.AuthRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authManager) {
		this.authenticationManager = authManager;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			AuthRequest credentials = new ObjectMapper().readValue(request.getInputStream(), AuthRequest.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					credentials.getUsername(), credentials.getPassword());

			Authentication authentication = authenticationManager.authenticate(authToken);
			
			return authentication;
		}
		catch(Exception e) {
			String exceptionMessage = "Exception in attemptAuth : " + e.getMessage();
			response.addHeader("Authorization", exceptionMessage);
			return null;
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		final String SECRET_KEY = "secretsecretsecretsecretsecretsecretsecret";
		
		String jwtToken = Jwts.builder()
				.setSubject(authResult.getName())
//				.setClaims(claims)
				.claim("authorities", authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1 * 1000))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
				.compact();
		
		response.addHeader("Authorization", "Bearer "+jwtToken);
	}

}

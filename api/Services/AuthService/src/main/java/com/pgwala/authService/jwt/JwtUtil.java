package com.pgwala.authService.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String SECRET_KEY = "secretsecretsecretsecretsecretsecretsecret";
	
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
//		Date exp = extractClaim(token, Claims::getExpiration);
//		System.out.println("Expiration Time : " + exp);
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//		claims.put("authorities", userDetails.getAuthorities());
		return createToken(userDetails.getAuthorities(), userDetails.getUsername());
	}
	
	public String createToken(Collection<? extends GrantedAuthority> claims, String subject) {
		return Jwts.builder().setSubject(subject)
//							.setClaims(claims)
							.claim("authorities", claims.stream().map(item -> item.getAuthority()).collect(Collectors.toList()))
							.setIssuedAt(new Date(System.currentTimeMillis()))
							.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1 * 1000))
							.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
							.compact();
							
	}
	
}

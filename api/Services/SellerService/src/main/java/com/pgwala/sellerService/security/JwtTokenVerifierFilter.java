package com.pgwala.sellerService.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		
		if(header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String SECRET_KEY = "secretsecretsecretsecretsecretsecretsecret";
		
		String token = header.replace("Bearer ", "");
		
		try {
			Claims claims = Jwts.parser()
								.setSigningKey(SECRET_KEY.getBytes())
								.parseClaimsJws(token)
								.getBody();
			
			String username = claims.getSubject();
			
			if(username != null) {
				List<String> authorities = (List<String>) claims.get("authorities");
				System.out.println(authorities);
				
				Authentication auth = new UsernamePasswordAuthenticationToken(username,
																			null, 
																			authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		catch(Exception e) {
			System.out.println("Execption in tokenVerifier : "+e.getMessage());
		}
		
		filterChain.doFilter(request, response);
		
	}

}

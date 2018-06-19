package com.freelancer.security.config;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freelancer.model.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	// EXPIRATION_TIME = 10 dias
		public static final long EXPIRATION_TIME = 860_000_000L;
		public static final String SECRET = "sikeodk";
		public static final String TOKEN_PREFIX = "Bearer";
		public static final String HEADER_STRING = "Authorization";

	
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
			try {
				UserEntity userEntity  = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
				
				return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,	HttpServletResponse response,FilterChain filterChain,Authentication auth) throws IOException, ServletException {
		ZonedDateTime expirationTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_TIME, ChronoUnit.MILLIS);
		String token = Jwts.builder().setSubject(((User)auth.getPrincipal()).getUsername()).setExpiration(Date.from(expirationTimeUTC.toInstant()))
		.signWith(SignatureAlgorithm.HS256, SECRET).compact();
		
		response.getWriter().write(TOKEN_PREFIX + token);
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token );
	
		
	}
	
	

}

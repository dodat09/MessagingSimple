package com.demo.chat.Service;

import java.util.Map;

import com.demo.chat.Model.User;

import io.jsonwebtoken.Claims;

public interface JwtService {
	
	String createToken(User user);
	
	String refreshToken(Map<String,Object> claims ,User user);
	
	Claims getClaims(String token);
	
	boolean validateToken(String token);
	
	String getSubjectToken(String token );
	
	String getIssured(String token);
	
	
}

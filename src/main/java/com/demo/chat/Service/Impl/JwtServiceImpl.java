package com.demo.chat.Service.Impl;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.demo.chat.Model.User;
import com.demo.chat.Service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtServiceImpl implements JwtService {
	private final static Logger logger = LoggerFactory.getLogger(JwtService.class.getName());
	private final static String secret_key="chia khoa day ne ";
	private final static long ex= 24*24*3600;
	
	
    //auhtentication chua cac thong tin dang nhap cua user
	@Override
	public String createToken(User user) {
		//User user =(User) authentication.getPrincipal();
		return Jwts.builder()
				   .setSubject(user.getUsername()+" : "+user.getPassword())
				   .setIssuer(user.getUsername())
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis()+ex))
				   .signWith(SignatureAlgorithm.HS512, secret_key)
				   .compact();               
	}
	//refresh lại token
	@Override
	public String refreshToken(Map<String,Object> claims ,User user) {
		return Jwts.builder().setClaims(claims)
				   .setSubject(user.getUsername()+" : "+user.getPassword())
				   .setIssuer(user.getUsername())
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis()+ex))
				   .signWith(SignatureAlgorithm.HS512, secret_key)
				   .compact(); 
	}
	//giai ma token va lay cac thong tin ma token chua dung
	public Claims getClaims(String token) {
		return Jwts.parser()
				   .setSigningKey(secret_key)
				   .parseClaimsJws(token)
				   .getBody();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret_key).parsePlaintextJws(token).getBody();
			return true;
		}catch(ExpiredJwtException e) {// loi het han ma token 
			logger.error("Expired jwt token ->message: {} ",e);
		}catch(SignatureException e) {//chu ky khong hop le
			logger.error("Invalid Jwt signature -> message: {}",e);
		}catch(MalformedJwtException e) {//xay ra khi ma token khong dung dinhj dang
			logger.error("Invalid Jwt token ->message: {}",e);
		}catch(IllegalArgumentException e) {
			logger.error("Token claims is empty ->message:{}",e);
		}catch(UnsupportedJwtException e) {//goi cac chuc nang khong co san trong thu vien
			logger.error("Unsupported Jwt ->message:{}",e);
		}
		return false;
	}
	public String getSubjectToken(String token ) {
		return Jwts.parser().parseClaimsJws(token).getBody().getSubject();
	}
	public String getIssured(String token) {
		return Jwts.parser().parseClaimsJws(token).getBody().getIssuer();
	}
}

package com.demo.chat.Service.Impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.chat.Model.Role;
import com.demo.chat.Model.User;
import com.demo.chat.Model.Detail.JwtAuthenticationResponse;
import com.demo.chat.Model.Detail.RefreshTokenRequest;
import com.demo.chat.Model.dto.UserDto;
import com.demo.chat.Repository.UserRepo;
import com.demo.chat.Service.AuthenticationService;

@Service

public class AuthenticationServiceImpl implements AuthenticationService{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class.getName());
    
	@Autowired
	private UserRepo userRepo;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtServiceImpl jwtService;

	@Override
	public User sighup(UserDto userDto) {
		User user = new User();
		Role role = new Role();
		role.setRolename("USER");
	    Set<Role> setRole = new HashSet<>();
	    setRole.add(role);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setUsername(userDto.getUsername());
		user.setCreateTime(LocalDateTime.now());
		user.setUserRole(setRole);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		 return userRepo.save(user);
	}

	@Override
	public JwtAuthenticationResponse sighin(UserDto userDto) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword()));
		User user = userRepo.findByUsername(userDto.getUsername());
		if(user==null) {
			logger.error("User co ten " + userDto.getUsername()+ " khong ton tai");
		}
		String token = jwtService.createToken(user);
		String  refreshToken = jwtService.refreshToken(new HashMap<>(), user);
		JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse();
		jwtResponse.setToken(token);
		jwtResponse.setRefreshToken(refreshToken);
		return jwtResponse;
	}

	@Override
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
		JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse();
		String userName= jwtService.getIssured(refreshToken.getToken());
		User user = userRepo.findByUsername(userName);
		if(user==null) {
			logger.error("User khong ton tai");
		}else {
			if(jwtService.validateToken(refreshToken.getToken())) {
			String token = jwtService.createToken(user);
			jwtResponse.setToken(token);
			jwtResponse.setRefreshToken(refreshToken.getToken());
			return jwtResponse;
		   }
		
		}
		return null;
	}
	
	
	
}

package com.demo.chat.Service;

import com.demo.chat.Model.User;
import com.demo.chat.Model.Detail.JwtAuthenticationResponse;
import com.demo.chat.Model.Detail.RefreshTokenRequest;
import com.demo.chat.Model.dto.UserDto;

public interface AuthenticationService {

	public User sighup(UserDto userDto);
	
	public JwtAuthenticationResponse sighin(UserDto userDto);
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);
}

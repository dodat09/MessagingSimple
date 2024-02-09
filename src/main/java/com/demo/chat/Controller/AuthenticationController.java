package com.demo.chat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.chat.Model.User;
import com.demo.chat.Model.Detail.JwtAuthenticationResponse;
import com.demo.chat.Model.Detail.RefreshTokenRequest;
import com.demo.chat.Model.dto.UserDto;
import com.demo.chat.Service.Impl.AuthenticationServiceImpl;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthenticationController {
    
	@Autowired
	private AuthenticationServiceImpl authenticationService;
	
	
	
@PostMapping("/sighup")
private ResponseEntity<User> sighupUser(@RequestBody UserDto userDto){
	return ResponseEntity.ok(authenticationService.sighup(userDto));
	//authenticationService.sighup(userDto);
}

@PostMapping("/sighin")
private ResponseEntity<JwtAuthenticationResponse> sighinUser(@RequestBody UserDto userDto){
	return ResponseEntity.ok(authenticationService.sighin(userDto));
}

@PostMapping("/refreshtoken")
private ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshToken){
	return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
}
@GetMapping("/test")
private String test() {
	return "heelloo";
}
}

package com.demo.chat.Model.Detail;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.chat.Model.User;
import com.demo.chat.Repository.UserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
   
	
	@Autowired
	private UserRepo userRepo;
	
	private ConvertRole convertRole;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	     User user = userRepo.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User is not found : "+username);
		}
//		Collection<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//		GrantedAuthority authority = new SimpleGrantedAuthority();
//		grantList.add(authority);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				convertRole.convertRole(user));
				
	}

}

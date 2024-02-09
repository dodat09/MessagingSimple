package com.demo.chat.Model.Detail;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.demo.chat.Model.User;

public class ConvertRole {
   public Collection<GrantedAuthority> convertRole(User user){
	   return user.getUserRole().stream()
			      .map(role->new SimpleGrantedAuthority(role.getRolename()))
                  .collect(Collectors.toList());
   }
}

package com.demo.chat.Model.Detail;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.chat.Model.User;

public class CustomerUserDetail implements UserDetails {
     private String username;
     private String password;
     private Collection<? extends GrantedAuthority> role;
     
     
     public CustomerUserDetail(User user) {
		super();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.role = convertRole.convertRole(user);
	}

	private ConvertRole convertRole;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

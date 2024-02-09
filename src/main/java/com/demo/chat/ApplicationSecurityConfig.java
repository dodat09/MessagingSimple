package com.demo.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.chat.Jwt.JwtAuthenticationFilter;
import com.demo.chat.Model.Detail.UserDetailServiceImpl;
@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig extends  WebSecurityConfigurerAdapter{

     @Autowired
     private UserDetailServiceImpl userDetail;
     
     @Autowired
     private JwtAuthenticationFilter jwtAuthenticationFilter;
     
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetail).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}
	
    
	@Bean
	public AuthenticationManager setAuthenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		// 1 interface dung de so sanh truc tiep voi du lieuj trong da
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetail);
		dao.setPasswordEncoder(passwordEncoder());
		return dao;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		    .antMatchers("/auth/**").permitAll()
		   
		    .antMatchers("/message").hasAnyRole("User")
		    .anyRequest().permitAll();
		  
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
    
	

	
             
}

package com.demo.chat.Jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.chat.Model.User;
import com.demo.chat.Model.Detail.CustomerUserDetail;
import com.demo.chat.Model.Detail.UserDetailServiceImpl;
import com.demo.chat.Service.Impl.JwtServiceImpl;

@Component
public class JwtAuthenticationFilter<UserPasswordAuthenticationToken> extends OncePerRequestFilter {
 
	
	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 if(!hasAuthorizationHeader(request)) {
			 filterChain.doFilter(request, response);
			 return;
		 }
		 String token = getAccessToken(request);
		 if(!jwtServiceImpl.validateToken(token)) {
			 filterChain.doFilter(request, response);
			 return;
		 }
		 getAuthenticationContext(request,token);
		 filterChain.doFilter(request, response);
		
	}
	//laasy thong tin user va chuyen len security
	private void getAuthenticationContext(HttpServletRequest request,String token) {
		//lay thong tin user thong qua token
		UserDetails user = getUserDetail(token);
		//tao mot doi tuong de luu tru thong tin 
		UsernamePasswordAuthenticationToken atoken = new 
				UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		//set lai thong tin detail tu doi tuong
		atoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		//chuyen thong tin len securitycontext de dex dang lay du lieu
		SecurityContextHolder.getContext().setAuthentication(atoken);
	}
	//laay thong tin chi tiet nguoi dung tu accesstoken
	private UserDetails getUserDetail(String token) {
		String userName = jwtServiceImpl.getIssured(token);
		CustomerUserDetail userDetail =(CustomerUserDetail) userDetailService.loadUserByUsername(userName);
		return userDetail;
	}
	//kiem tra token da hơp lệ hay chưa
	private boolean hasAuthorizationHeader(HttpServletRequest request) {
		String auth= request.getHeader("Authorization");
		if(ObjectUtils.isEmpty(auth)||!auth.startsWith("Bearer")) {
			return false;
		}
		return true;
	}
	//lây ma token trong reques gửi lên
   private String getAccessToken(HttpServletRequest request) {
	   String auth = request.getHeader("Authorization");
	   String jwt = auth.substring(7);
	   System.out.println("Token in request : "+jwt);
	   return jwt;
	   
   }
   
}

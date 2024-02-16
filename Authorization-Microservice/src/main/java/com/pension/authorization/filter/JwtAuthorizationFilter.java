package com.pension.authorization.filter;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pension.authorization.service.MyUserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{


	@Autowired
	private MyUserService service;
	

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header= request.getHeader("Authorization");
		if(header==null || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authentication=getAuthentication(request);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token= request.getHeader("Authorization");
		if(token!=null) {
			Jws<Claims> jws;
			try {
				jws=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token.replace("Bearer ", ""));
				String user=jws.getBody().getSubject();
				if(user!=null) {
					UserDetails userInfo= service.loadUserByUsername(user);
					return new UsernamePasswordAuthenticationToken(userInfo.getUsername(),null,userInfo.getAuthorities());
				}
			}
			catch(JwtException e) {
				return null;
			}
			return null;
		}
		return null;
	}	
	
}

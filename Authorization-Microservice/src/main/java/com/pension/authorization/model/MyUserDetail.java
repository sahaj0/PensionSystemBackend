package com.pension.authorization.model;

import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pension.authorization.controller.AuthController;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyUserDetail implements UserDetails{
	
	private static final Logger log = LoggerFactory.getLogger(MyUserDetail.class);

	private static final long serialVersionUID = 1L;
	
	private transient User user;
	
	public MyUserDetail(User user) {
		this.user=user;
	}
	
	
	//Assigning Role to the user
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(user.getRole().equalsIgnoreCase("Admin")) {
			log.info("Admin Role Assigned..");
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		log.info("No Role is available..");
		return Arrays.asList(new SimpleGrantedAuthority(""));
	}

	//getting password from user.
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	//getting username from user.
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//checking if account is expired.
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//checking if user is ban.
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//Checking if credential is expired.
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	//Checking if user account is enabled.
	@Override
	public boolean isEnabled() {
		return true;
	}

}

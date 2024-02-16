package com.pension.authorization.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pension.authorization.controller.AuthController;
import com.pension.authorization.dao.UserDao;
import com.pension.authorization.model.MyUserDetail;
import com.pension.authorization.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserService implements UserDetailsService{
	private static final Logger log = LoggerFactory.getLogger(MyUserService.class);
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Returning UserDetails Object if your is present in database.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("getting user from username");
		User user= userDao.getUserByUsername(username);
		if(user==null) {
			log.warn("no users available");
			throw new UsernameNotFoundException("No Users Available With "+username+" !!!....");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		log.info("user found with the provided username");
		return new MyUserDetail(user);
	}

}

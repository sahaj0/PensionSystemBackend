package com.pension.authorization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pension.authorization.model.User;
import com.pension.authorization.repository.UserRepository;

@Repository
public class UserDao {
 
 @Autowired
 private UserRepository userRepository;
	
 public User getUserByUsername(String username) {
	 return userRepository.findByUsername(username);
 } 
}

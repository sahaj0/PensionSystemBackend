package com.pension.authorization.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void userNoArgsTest() {
		User user=new User();
		assertNotNull(user);
	}
	
	@Test
	void userAllArgsTest() {
		User user=new User(1L,"luke","luke","admin");
		User expected=new User();
		expected.setId(user.getId());
		expected.setUsername(user.getUsername());
		expected.setPassword(user.getPassword());
		expected.setRole(user.getRole());
		assertThat(expected).usingRecursiveComparison().isEqualTo(user);
	}

}

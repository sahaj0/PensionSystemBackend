package com.pension.authorization.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pension.authorization.model.User;
import com.pension.authorization.repository.UserRepository;

@SpringBootTest(classes = UserDao.class)
class UserDaoTest {

	@Autowired
	UserDao dao;
	
	@MockBean
	UserRepository repo;
	
	@Test
	void test() {
		User user=new User(1L, "luke", "password", "admin");
		when(repo.findByUsername("luke")).thenReturn(user);
		User user2=dao.getUserByUsername("luke");
		User expectedUser=new User(1L, "luke", "password", "admin");
		assertThat(expectedUser).usingRecursiveComparison().isEqualTo(user2);
	}

}

package com.pension.authorization.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.pension.authorization.exception.AuthenticationException;
import com.pension.authorization.model.MyUserDetail;
import com.pension.authorization.model.User;
import com.pension.authorization.model.UserRequest;
import com.pension.authorization.service.MyUserService;

@SpringBootTest(classes = AuthController.class)
@RunWith(SpringRunner.class)
class AuthControllerTest {


	@Autowired
	private AuthController controller;

	@MockBean
	MyUserService service;
	
	@MockBean
	PasswordEncoder encoder;
	

	
	@Test
	void loginSuccessTest() throws Exception {
		when(encoder.encode("luke")).thenReturn("ekul");
		when(encoder.matches("ekul", "ekul")).thenReturn(true);
		UserDetails details=new MyUserDetail(new User(1L, "luke", encoder.encode("luke"), "admin"));
		when(service.loadUserByUsername("luke")).thenReturn(details);
		UserRequest req=new UserRequest("luke",details.getPassword());
		ResponseEntity<Map<String, String>> map= controller.getAuthenticationToken(req);
		assertEquals(200,map.getStatusCodeValue());
	}
	
	@Test
	void loginFailedTest() throws Exception {
		
		when(encoder.encode("luke")).thenReturn("ekul");
		when(encoder.matches("ekul", "ekul")).thenReturn(true);
		UserDetails details=new MyUserDetail(new User(1L, "luke", encoder.encode("luke"), "admin"));
		when(service.loadUserByUsername("luke")).thenReturn(details);
		UserRequest req=new UserRequest("luke","luke");
//		ResponseEntity<Map<String, String>> map= controller.getAuthenticationToken(req);
//		assertEquals(200,map.getStatusCodeValue());
		Exception e=assertThrows(AuthenticationException.class, ()->controller.getAuthenticationToken(req));
		assertEquals("Invalid Credentials.", e.getMessage());
	}
	
	
	
	@Test
	void successfullAuthorizationTest() throws AuthenticationException, Exception {
		when(encoder.encode("luke")).thenReturn("ekul");
		when(encoder.matches("ekul", "ekul")).thenReturn(true);
		UserDetails details=new MyUserDetail(new User(1L, "luke", encoder.encode("luke"), "admin"));
		when(service.loadUserByUsername("luke")).thenReturn(details);
		UserRequest req=new UserRequest("luke",details.getPassword());
		ResponseEntity<Map<String, String>> map= controller.getAuthenticationToken(req);
		assertTrue(controller.authorizeAdmin("Bearer "+map.getBody().get("token")));
	}
	
	@Test
	void failedAuthorizationTest() throws AuthenticationException, Exception {
		
		assertFalse(controller.authorizeAdmin("Bearer badToken"));
	}
	
	@Test
	void failedAuthorizationRequestHeaderNullTest() throws Exception {
		assertFalse(controller.authorizeAdmin(null));
	}
	
	@Test
	void failedAuthorizationRequestHeaderNotStartingWithBearerTest() throws Exception {
		assertFalse(controller.authorizeAdmin("abcde skdknd"));
	}
	

}



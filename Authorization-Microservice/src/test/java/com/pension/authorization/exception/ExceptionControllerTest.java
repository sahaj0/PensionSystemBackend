package com.pension.authorization.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pension.authorization.model.ErrorModel;

@SpringBootTest(classes = ExceptionControllerTest.class)
class ExceptionControllerTest {

	@InjectMocks
	private ExceptionController handler;
	
	@Mock
	ErrorModel errorModel;
	
	@BeforeEach
	void setupErrorModel() {
		errorModel=new ErrorModel(HttpStatus.BAD_REQUEST.value(),"invalid creds", String.valueOf(new Date()));
	}
	
	@Test
	void authenticationExceptionTest() {
		
		AuthenticationException exp=new AuthenticationException("invalid creds");
		handler.handleAuthenticationException(exp);
		ResponseEntity<ErrorModel> entityModel=new ResponseEntity<ErrorModel>(errorModel,HttpStatus.OK);
		assertEquals(400, entityModel.getBody().getErrorId());
	}
	
	@Test
	void usernameNotFoundExceptionTest() {
		
		UsernameNotFoundException exp=new UsernameNotFoundException("No Users Available With that username !!!....");
		handler.handleUsernameNotFoundException(exp);
		ResponseEntity<ErrorModel> entityModel=new ResponseEntity<ErrorModel>(errorModel,HttpStatus.OK);
		assertEquals(400, entityModel.getBody().getErrorId());
	}
	
	

}

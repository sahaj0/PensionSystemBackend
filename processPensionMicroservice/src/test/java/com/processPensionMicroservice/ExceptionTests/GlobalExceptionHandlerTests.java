package com.processPensionMicroservice.ExceptionTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.processPensionMicroservice.exception.AuthorizationException;
import com.processPensionMicroservice.exception.GlobalExceptionHandler;
import com.processPensionMicroservice.exception.PensionerDetailsException;
import com.processPensionMicroservice.exception.PensionerNotFoundException;
import com.processPensionMicroservice.model.CustomErrorResponse;



@SpringBootTest(classes = GlobalExceptionHandlerTests.class)
public class GlobalExceptionHandlerTests {
	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;
	@Mock
	CustomErrorResponse customErrorResponse;
	@BeforeEach
	void setUp() {
	List<String> message=new ArrayList<String>();
	message.add("test message");
		customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND,"Error",message);
	}
	
	@Test
	void handlesPensionerNotFoundExceptionTest() {
		PensionerNotFoundException pensionerNotFoundException = new PensionerNotFoundException("Pensioner not found");
		globalExceptionHandler.handlePensionerNotFoundException(pensionerNotFoundException);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
		assertEquals(404, entity.getStatusCodeValue());
	}
	
	@Test
	void handlePensionerDetailsExceptionTest() {
		PensionerDetailsException pensionerDetailsException = new PensionerDetailsException("Invalid Pensioner detail Provided.");
		globalExceptionHandler.handlePensionerDetailsException(pensionerDetailsException);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
		assertEquals(404, entity.getStatusCodeValue());
	}
	
	@Test
	void handleAuthorizationExceptionTest() {
		AuthorizationException authorizationException = new AuthorizationException("Unauthorized Access");
		globalExceptionHandler.handleAuthorizationException(authorizationException);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
		assertEquals(400, entity.getStatusCodeValue());
	}
	
	@Test
	void handlesExceptionTest() {
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
		assertEquals(400, entity.getStatusCodeValue());
	}
	
	
	
}


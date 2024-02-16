package com.pensionerDisbursmentMicroservice.exception;

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

import com.pensionerDisbursementMicroservice.Exception.GlobalExceptionHandler;
import com.pensionerDisbursementMicroservice.Exception.PensionerDetailNotFoundException;
import com.pensionerDisbursementMicroservice.Model.CustomErrorResponse;

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
	void handlesPensionerDetailNotFoundExceptionTest() {
		PensionerDetailNotFoundException pensionerDetailNotFoundException = new PensionerDetailNotFoundException("Pensioner not found");
		globalExceptionHandler.handlePensionerDetailNotFoundException(pensionerDetailNotFoundException);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
		assertEquals(404, entity.getStatusCodeValue());
	}
	
	@Test
	void handlesExceptionTest() {
		Exception exception = new Exception("Feign Exception or NumberFormat Exception");
		globalExceptionHandler.handleException(exception);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
		assertEquals(400, entity.getStatusCodeValue());
	}
	
}


package com.processPensionMicroservice.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.processPensionMicroservice.custom_annotation.Generated;
import com.processPensionMicroservice.model.CustomErrorResponse;
import feign.RetryableException;

@RestControllerAdvice
@Generated
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(PensionerNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handlePensionerNotFoundException(PensionerNotFoundException ex){
		CustomErrorResponse customResponse=new CustomErrorResponse();
		customResponse.setTimestamp(LocalDateTime.now());
		customResponse.setMessage(Arrays.asList(ex.getMessage()));
		customResponse.setReason("Invalid Details provided");
		customResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(PensionerDetailsException.class)
	public ResponseEntity<CustomErrorResponse> handlePensionerDetailsException(PensionerDetailsException ex){
		CustomErrorResponse customResponse=new CustomErrorResponse();
		customResponse.setTimestamp(LocalDateTime.now());
		customResponse.setMessage(Arrays.asList(ex.getMessage()));
		customResponse.setReason("Invalid Pensioner detail Provided.");
		customResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<CustomErrorResponse> handleAuthorizationException(AuthorizationException ex){
		CustomErrorResponse customResponse=new CustomErrorResponse();
		customResponse.setTimestamp(LocalDateTime.now());
		customResponse.setMessage(Arrays.asList(ex.getMessage()));
		customResponse.setReason("Unauthorized Access");
		customResponse.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.OK);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
       
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<String> errors = fieldErrors.stream()
					.map(err -> err.getField() +" : " + err.getDefaultMessage())
					.collect(Collectors.toList());
		CustomErrorResponse customResponse=new CustomErrorResponse();
		customResponse.setTimestamp(LocalDateTime.now());
        customResponse.setMessage(errors);
        customResponse.setReason("Please provide all inputs in correct manner");
        customResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(customResponse,headers,HttpStatus.OK);
	}
	
	@ExceptionHandler(RetryableException.class)
    public ResponseEntity<CustomErrorResponse> handleException(RetryableException exception){
        CustomErrorResponse customResponse=new CustomErrorResponse();
        customResponse.setTimestamp(LocalDateTime.now());
        customResponse.setMessage(Arrays.asList(exception.getMessage()));
        customResponse.setReason("Invalid Request Information");
        customResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.OK);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleAuthorizationException(Exception ex){
        CustomErrorResponse customResponse=new CustomErrorResponse();
        customResponse.setTimestamp(LocalDateTime.now());
        customResponse.setMessage(Arrays.asList(ex.getMessage()));
        customResponse.setReason("You must be authorized to access this information");
        customResponse.setStatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.OK);
    }
}

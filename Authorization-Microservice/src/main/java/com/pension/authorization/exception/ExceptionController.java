package com.pension.authorization.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pension.authorization.custom_annotation.Generated;
import com.pension.authorization.model.ErrorModel;


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{

	@Generated
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
       
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		List<String> errors=lambdaExpressionForFieldErrors(fieldErrors);
		ErrorModel customResponse=new ErrorModel();
		customResponse.setErrorLogTime(""+new Date());
        customResponse.setErrorText(errors.toString());
        customResponse.setErrorId(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.OK).body(customResponse);
	}
	@Generated
	public List<String> lambdaExpressionForFieldErrors(List<FieldError> fieldErrors) {
		return fieldErrors.stream()
				.map(err -> err.getField() +" : " + err.getDefaultMessage())
				.collect(Collectors.toList());
	}
	@ExceptionHandler
	public ResponseEntity<ErrorModel>handleAuthenticationException(AuthenticationException e){
		ErrorModel error= new ErrorModel();
		error.setErrorId(HttpStatus.BAD_REQUEST.value());
		error.setErrorText(e.getMessage());
		error.setErrorLogTime(""+new Date());
		return ResponseEntity.status(HttpStatus.OK).body(error);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorModel>handleUsernameNotFoundException(UsernameNotFoundException e){
		ErrorModel error= new ErrorModel();
		error.setErrorId(HttpStatus.BAD_REQUEST.value());
		error.setErrorText(e.getMessage());
		error.setErrorLogTime(""+new Date());
		return ResponseEntity.status(HttpStatus.OK).body(error);
	}
	

}

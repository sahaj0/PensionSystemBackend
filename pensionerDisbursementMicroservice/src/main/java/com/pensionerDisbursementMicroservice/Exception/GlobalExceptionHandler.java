package com.pensionerDisbursementMicroservice.Exception;

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

import com.pensionerDisbursementMicroservice.Model.CustomErrorResponse;
import com.pensionerDisbursementMicroservice.custom_annotation.Generated;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(PensionerDetailNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handlePensionerDetailNotFoundException(PensionerDetailNotFoundException ex){
		CustomErrorResponse customResponse=new CustomErrorResponse();
		customResponse.setTimestamp(LocalDateTime.now());
		customResponse.setMessage(Arrays.asList(ex.getMessage()));
		customResponse.setReason("Invalid Details provided");
		customResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.OK);
	}
	
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception ex){
        CustomErrorResponse customResponse=new CustomErrorResponse();
        customResponse.setTimestamp(LocalDateTime.now());
//        String msg=ex.getMessage().substring(ex.getMessage().indexOf("message")+10,ex.getMessage().length()-3);
        customResponse.setMessage(Arrays.asList(ex.getMessage()));
        customResponse.setReason("Invalid Request Information");
        customResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.BAD_REQUEST);
    }
	
	@Generated
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
        customResponse.setStatus(HttpStatus.OK);
        return new ResponseEntity<>(customResponse,headers,customResponse.getStatus());
	}
}

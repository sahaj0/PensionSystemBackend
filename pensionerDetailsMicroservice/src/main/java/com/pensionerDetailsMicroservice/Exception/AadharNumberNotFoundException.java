package com.pensionerDetailsMicroservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AadharNumberNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AadharNumberNotFoundException(String msg) {
		super(msg);
	}
	
}

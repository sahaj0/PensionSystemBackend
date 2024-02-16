package com.pensionerDisbursementMicroservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PensionerDetailNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public PensionerDetailNotFoundException(String msg) {
		super(msg);
	}
}

package com.processPensionMicroservice.exception;

import java.util.Date;

import feign.Request;
import feign.Request.HttpMethod;
import feign.RetryableException;

public class ServiceNotFound extends RetryableException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4833601190866805897L;

	public ServiceNotFound(int status, String message, HttpMethod httpMethod, Date retryAfter, Request request) {
		super(status, "Seems the other service is done", httpMethod, retryAfter, request);
		
	}
	
}

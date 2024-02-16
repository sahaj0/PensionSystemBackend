package com.processPensionMicroservice.exception;

public class AuthorizationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String msg){
		super(msg);
	}

}

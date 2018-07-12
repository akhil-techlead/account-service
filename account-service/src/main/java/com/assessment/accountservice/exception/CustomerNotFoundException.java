package com.assessment.accountservice.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Akhilesh
 *
 */
public class CustomerNotFoundException extends ApiException{

	private static final long serialVersionUID = 1L;	

	/**
	 * @param message
	 * @param status
	 */
	public CustomerNotFoundException(String message, HttpStatus status) {
		super(message, status);
	}
}

package com.assessment.accountservice.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Akhilesh
 *
 */
public class ApiException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private HttpStatus status;
	

	/**
	 * @param message
	 * @param status
	 */
	public ApiException(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

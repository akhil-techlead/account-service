package com.assessment.accountservice.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Akhilesh
 *
 */
public class InSufficientBalanceException extends ApiException{

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param status
	 */
	public InSufficientBalanceException(String message, HttpStatus status) {
		super(message, status);
	}
}

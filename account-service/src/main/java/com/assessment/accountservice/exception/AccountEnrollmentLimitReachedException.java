package com.assessment.accountservice.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Akhilesh
 *
 */
public class AccountEnrollmentLimitReachedException extends ApiException{

	private static final long serialVersionUID = 1L;

	public AccountEnrollmentLimitReachedException(String message, HttpStatus status) {
		super(message, status);
	}

}

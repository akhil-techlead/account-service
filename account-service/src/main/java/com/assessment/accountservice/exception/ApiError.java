package com.assessment.accountservice.exception;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError {
	
	   private HttpStatus status;
	   private String message;
	   
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus httpStatus) {
		this.status = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

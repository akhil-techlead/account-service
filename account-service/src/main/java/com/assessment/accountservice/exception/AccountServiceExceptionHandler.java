package com.assessment.accountservice.exception;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Akhilesh
 *
 */

@ControllerAdvice
public class AccountServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ InSufficientBalanceException.class })
	public ResponseEntity<ApiError> handleInSufficientBalance(InSufficientBalanceException ex) {
		return error(ex);
	}

	@ExceptionHandler({ CustomerNotFoundException.class })
	public ResponseEntity<ApiError> handleCustomerNotFound(CustomerNotFoundException ex) {
		return error(ex);
	}

	@ExceptionHandler({ AccountEnrollmentLimitReachedException.class })
	public ResponseEntity<ApiError> handleCustomerNotFound(AccountEnrollmentLimitReachedException ex) {
		return error(ex);
	}

	@ExceptionHandler({ DataAccessException.class })
	public ResponseEntity<ApiError> handleCustomerNotFound(DataAccessException ex) {
		return new ResponseEntity<>(buildApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Internal System Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<ApiError> errorResponse(ApiError apiError) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ApiError>(apiError, headers, apiError.getStatus());
	}

	/**
	 * @param ex
	 * @return
	 */
	private ResponseEntity<ApiError> error(ApiException ex) {
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getMessage());
		apiError.setStatus(ex.getStatus());
		return errorResponse(apiError);
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<ApiError> handle(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		StringBuilder builder = new StringBuilder();
		for (ConstraintViolation<?> violation : violations) {
			builder.append(violation.getMessage() + "\n");
		}
		return errorResponse(buildApiError(HttpStatus.BAD_REQUEST, builder.toString().trim()));
	}

	/* 
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(buildApiError(HttpStatus.BAD_REQUEST,ex.getBindingResult().getFieldError().getDefaultMessage().toString().trim()), HttpStatus.BAD_REQUEST);
	}
	
	private ApiError buildApiError(HttpStatus status, String message) {
		ApiError apiError = new ApiError();
		apiError.setStatus(status);
		apiError.setMessage(message);	
		return apiError;
	}
}

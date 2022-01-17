package com.askc.insurance.rest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.askc.insurance.rest.exception.PolicyNotValidException;

/**
 * Handler for exceptions that occur during processing of a REST request
 * 
 * @author svkolev
 *
 */
@ControllerAdvice
public class RestExceptionHandler {

	/**
	 * Handler for {@link PolicyNotValidException}s
	 * 
	 * @param ex
	 *            the {@link PolicyNotValidException}
	 * @return the exception message and {@link HttpStatus}
	 */
	@ResponseBody
	@ExceptionHandler(PolicyNotValidException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	String policyNotValidException(PolicyNotValidException ex) {
		return ex.getMessage();
	}
}

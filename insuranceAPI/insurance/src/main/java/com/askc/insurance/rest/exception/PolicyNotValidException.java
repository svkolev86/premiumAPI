package com.askc.insurance.rest.exception;

/**
 * Exception in case a policy is not valid
 * 
 * @author svkolev
 *
 */
public class PolicyNotValidException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message
	 *            the exception message
	 */
	public PolicyNotValidException(String message) {
		super(message);
	}
}

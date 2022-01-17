package com.askc.insurance.service;

import com.askc.insurance.entity.Policy;
import com.askc.insurance.rest.exception.PolicyNotValidException;

/**
 * Interface for all calculator services
 * 
 * @author svkolev
 *
 */
public interface PremiumCalculator {

	/**
	 * Calculates the premium for provided {@link Policy}
	 * 
	 * @param policy
	 *            the {@link Policy}
	 * @return calculated premium
	 * @throws PolicyNotValidException
	 *             exception in case {@link Policy} is not valid
	 */
	public Double calculate(Policy policy) throws PolicyNotValidException;
}

package com.askc.insurance.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.askc.insurance.entity.Policy;
import com.askc.insurance.rest.exception.PolicyNotValidException;
import com.askc.insurance.service.PremiumCalculator;

/**
 * Policy REST Controller that proxies functionality
 * 
 * @author svkolev
 *
 */
@RestController
@RequestMapping("/policy")
public class PolicyController {

	@Autowired
	PremiumCalculator premiumCalculator;

	/**
	 * Calculates the premium for provided {@link Policy}
	 * 
	 * @param policy
	 *            the {@link Policy}
	 * @return calculated premium
	 * @throws PolicyNotValidException
	 *             exception in case a {@link Policy} is not valid
	 */
	@RequestMapping("/calculate")
	public Double calculatePolicy(@RequestBody(required = true) Policy policy) throws PolicyNotValidException {
		return premiumCalculator.calculate(policy);
	}

}

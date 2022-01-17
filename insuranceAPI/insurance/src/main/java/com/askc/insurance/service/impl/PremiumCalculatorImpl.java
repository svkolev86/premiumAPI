package com.askc.insurance.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.askc.insurance.entity.Policy;
import com.askc.insurance.entity.enums.PolicyRiskType;
import com.askc.insurance.rest.exception.PolicyNotValidException;
import com.askc.insurance.service.PremiumCalculator;
import com.askc.insurance.service.helper.CalculatorHelper;

/**
 * Implementation for {@link PremiumCalculator}
 * 
 * @author svkolev
 *
 */
@Service
public class PremiumCalculatorImpl implements PremiumCalculator {

	/*
	 * @see
	 * com.askc.insurance.service.PremiumCalculator#calculate(com.askc.insurance
	 * .entity.Policy)
	 */
	@Override
	public Double calculate(Policy policy) throws PolicyNotValidException {
		CalculatorHelper.verifyPolicy(policy);
		double theftPremium = calculateTheftPremium(policy);
		double firePremium = calculateFirePremium(policy);
		double totalSum = theftPremium + firePremium;
		double result = 0;
		if (totalSum > 0) {
			BigDecimal bd = new BigDecimal(Double.toString(totalSum));
			bd = bd.setScale(2, RoundingMode.HALF_EVEN);
			result = bd.doubleValue();
		}
		return result;
	}

	private double calculateTheftPremium(Policy policy) throws PolicyNotValidException {
		double theftSum = CalculatorHelper.caclulateInsuredSum(PolicyRiskType.THEFT, policy);
		double theftCoeficient = PolicyRiskType.THEFT.getCoefficient(theftSum);
		return theftSum * theftCoeficient;
	}

	private double calculateFirePremium(Policy policy) throws PolicyNotValidException {
		double fireSum = CalculatorHelper.caclulateInsuredSum(PolicyRiskType.FIRE, policy);
		double fireCoef = PolicyRiskType.FIRE.getCoefficient(fireSum);
		return fireSum * fireCoef;
	}
}

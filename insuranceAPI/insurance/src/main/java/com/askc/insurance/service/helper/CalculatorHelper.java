package com.askc.insurance.service.helper;

import org.springframework.util.Assert;

import com.askc.insurance.entity.Policy;
import com.askc.insurance.entity.PolicyObject;
import com.askc.insurance.entity.PolicySubObject;
import com.askc.insurance.entity.enums.PolicyRiskType;
import com.askc.insurance.rest.exception.PolicyNotValidException;

/**
 * Helper class for calculations
 * 
 * @author svkolev
 *
 */
public class CalculatorHelper {

	/**
	 * Calculates the sum of all {@link PolicySubObject} by
	 * {@link PolicyRiskType} in provided {@link Policy}
	 * 
	 * @param policyRiskType
	 *            the {@link PolicyRiskType}
	 * @param policy
	 *            the the {@link Policy}
	 * @return calculated sum
	 */
	public static double caclulateInsuredSum(PolicyRiskType policyRiskType, Policy policy) {
		return policy.getPolicyObjects().stream().map(PolicyObject::getPolicySubObjects)//
				.map(sOlist -> sOlist.stream().filter(o -> policyRiskType.equals(o.getPolicyRiskType())))//
				.mapToDouble((o) -> o.mapToDouble(PolicySubObject::getSum).sum()).sum();
	}

	/**
	 * Method for validating a {@link Policy}
	 * 
	 * @param policy
	 *            the {@link Policy}
	 * @throws PolicyNotValidException
	 *             exception in case a {@link Policy} is not valid
	 */
	public static void verifyPolicy(Policy policy) throws PolicyNotValidException {
		try {
			Assert.notNull(policy, "Policy must be initialized!");
			Assert.notEmpty(policy.getPolicyObjects(), "Policy must have objects!");

			policy.getPolicyObjects()
					.forEach(o -> Assert.notEmpty(o.getPolicySubObjects(), "Policy objects must exists!"));
			policy.getPolicyObjects().forEach(
					o -> o.getPolicySubObjects().forEach(o1 -> Assert.notNull(o1, "Policy sub object must exists!")));
		} catch (IllegalArgumentException e) {
			throw new PolicyNotValidException(e.getMessage());
		}
	}
}

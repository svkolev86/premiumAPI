package com.askc.insurance.junit;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.askc.insurance.entity.PolicyObject;
import com.askc.insurance.entity.enums.PolicyRiskType;
import com.askc.insurance.entity.enums.PolicyStatus;
import com.askc.insurance.helper.PolicyTestHelper;
import com.askc.insurance.rest.exception.PolicyNotValidException;
import com.askc.insurance.service.helper.CalculatorHelper;

/**
 * Unit test for testing {@link CalculatorHelper} methods
 * 
 * @author svkolev
 *
 */
public class CalculatorHelperTest {

	/**
	 * VErify all sums are calculated correctly
	 * 
	 * @throws Exception
	 *             any exception during execution
	 */
	@Test
	public void testSumming() throws Exception {
		PolicyTestHelper helper = new PolicyTestHelper("IP01", PolicyStatus.REGISTERED);
		PolicyObject beachHouse = helper.addPolicyObject("Beach house");
		helper.addPolicySubObject(beachHouse, "TV", PolicyRiskType.THEFT, 8);
		helper.addPolicySubObject(beachHouse, "PC", PolicyRiskType.THEFT, 4.55);
		helper.addPolicySubObject(beachHouse, "TV", PolicyRiskType.FIRE, 100);
		helper.addPolicySubObject(beachHouse, "PC", PolicyRiskType.FIRE, 3.23);

		Double theftSum = CalculatorHelper.caclulateInsuredSum(PolicyRiskType.THEFT, helper.getPolicy());
		Double fireSum = CalculatorHelper.caclulateInsuredSum(PolicyRiskType.FIRE, helper.getPolicy());

		Assertions.assertEquals(12.55, theftSum);
		Assertions.assertEquals(103.23, fireSum);
	}

	/**
	 * Verify calculations for incomplete <code>Policy</code>
	 * 
	 * @throws Exception
	 *             exception during execution
	 */
	@Test(expected = PolicyNotValidException.class)
	public void testPolicyNotvalidNoObjects() throws Exception {
		PolicyTestHelper helper = new PolicyTestHelper("IP01", PolicyStatus.REGISTERED);

		CalculatorHelper.verifyPolicy(helper.getPolicy());
	}

	/**
	 * Verify calculations for incomplete <code>Policy</code>
	 * 
	 * @throws Exception
	 *             exception during execution
	 */
	@Test(expected = PolicyNotValidException.class)
	public void testPolicyNotvalidNoSubObjects() throws Exception {
		PolicyTestHelper helper = new PolicyTestHelper("IP01", PolicyStatus.REGISTERED);
		helper.addPolicyObject("Beach house");

		CalculatorHelper.verifyPolicy(helper.getPolicy());
	}

	/**
	 * Test coefficients for different insured sums for
	 * {@link PolicyRiskType#THEFT}
	 * 
	 * @throws Exception
	 *             exception during execution
	 */
	@Test
	public void testTheftCoeficient() throws Exception {
		Assertions.assertEquals(0.11, PolicyRiskType.THEFT.getCoefficient(0d));
		Assertions.assertEquals(0.11, PolicyRiskType.THEFT.getCoefficient(14));
		Assertions.assertEquals(0.05, PolicyRiskType.THEFT.getCoefficient(15));
		Assertions.assertEquals(0.05, PolicyRiskType.THEFT.getCoefficient(16));
	}

	/**
	 * Test coefficients for different insured sums for
	 * {@link PolicyRiskType#FIRE}
	 * 
	 * @throws Exception
	 *             exception during execution
	 */
	@Test
	public void testFireCoeficient() throws Exception {
		Assertions.assertEquals(0.014, PolicyRiskType.FIRE.getCoefficient(0));
		Assertions.assertEquals(0.014, PolicyRiskType.FIRE.getCoefficient(99));
		Assertions.assertEquals(0.014, PolicyRiskType.FIRE.getCoefficient(100));
		Assertions.assertEquals(0.024, PolicyRiskType.FIRE.getCoefficient(101));
	}

}

package com.askc.insurance.helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.springframework.util.Assert;

import com.askc.insurance.entity.Policy;
import com.askc.insurance.entity.PolicyObject;
import com.askc.insurance.entity.PolicySubObject;
import com.askc.insurance.entity.enums.PolicyRiskType;
import com.askc.insurance.entity.enums.PolicyStatus;

/**
 * Helper class to support of {@link Policy} related tests
 * 
 * @author svkolev
 *
 */
public class PolicyTestHelper {

	/**
	 * Gets the contents of a resource file as string
	 * 
	 * @param filename
	 *            the filename of the file
	 * @return the contents of the file
	 */
	public static String getContentsOfResource(String filename) {
		final StringBuilder lines = new StringBuilder();
		try {
			Files.readAllLines(Paths.get(PolicyTestHelper.class.getResource(filename).toURI()))
					.forEach(l -> lines.append(l));
		} catch (URISyntaxException | IOException e) {
			Assertions.fail("Cannot read file", e);
			e.printStackTrace();
		}
		return lines.toString();
	}

	private Policy policy;

	/**
	 * Constructor.
	 * 
	 * @param number
	 *            {@link Policy} number
	 * @param policyStatus
	 *            status for the {@link Policy}
	 */
	public PolicyTestHelper(String number, PolicyStatus policyStatus) {
		policy = new Policy();
		policy.setNumber(number);
		policy.setPolicyStatus(policyStatus);
		policy.setPolicyObjects(new ArrayList<>());
	}

	/**
	 * Adds a {@link PolicyObject} to the {@link Policy}
	 * 
	 * @param name
	 *            name of the {@link PolicyObject}
	 * @return the created {@link PolicyObject}
	 */
	public PolicyObject addPolicyObject(String name) {
		PolicyObject policyObject = new PolicyObject();
		policyObject.setName(name);
		policyObject.setPolicySubObjects(new ArrayList<>());
		policy.getPolicyObjects().add(policyObject);
		return policyObject;
	}

	/**
	 * Adds a {@link PolicySubObject} to provided {@link PolicyObject}
	 * 
	 * @param policyObject
	 *            the {@link PolicyObject}
	 * @param name
	 *            {@link PolicySubObject}s name
	 * @param policyRiskType
	 *            the {@link PolicyRiskType} for the {@link PolicySubObject}
	 * @param insuredSum
	 *            insured sum for the {@link PolicySubObject}
	 * @return the created {@link PolicySubObject}
	 */
	public PolicySubObject addPolicySubObject(PolicyObject policyObject, String name, PolicyRiskType policyRiskType,
			double insuredSum) {
		Assert.notNull(policy.getPolicyObjects(), "Policy objects must be initialized!");
		Assert.isTrue(policy.getPolicyObjects().contains(policyObject), "Policy objects must contain provided object!");

		PolicySubObject policySubObject = new PolicySubObject();
		policySubObject.setName(name);
		policySubObject.setPolicyRiskType(policyRiskType);
		policySubObject.setSum(insuredSum);
		policyObject.getPolicySubObjects().add(policySubObject);
		return policySubObject;
	}

	/**
	 * @return generated {@link Policy}
	 */
	public Policy getPolicy() {
		return policy;
	}
}

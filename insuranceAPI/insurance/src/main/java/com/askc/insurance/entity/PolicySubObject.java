package com.askc.insurance.entity;

import com.askc.insurance.entity.enums.PolicyRiskType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Sub object that is part of an object (e.g. a TV in a House)
 * 
 * @author svkolev
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PolicySubObject extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * The sub object name
	 */
	String name;

	/**
	 * Insured sum
	 */
	Double sum;

	/**
	 * The insurance risk type
	 */
	PolicyRiskType policyRiskType;
}

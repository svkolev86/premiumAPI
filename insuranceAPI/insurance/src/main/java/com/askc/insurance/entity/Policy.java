package com.askc.insurance.entity;

import java.util.List;

import com.askc.insurance.entity.enums.PolicyStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Policy object that holds main policy attributes
 * 
 * @author svkolev
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Policy extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * The policy number
	 */
	private String number;

	/**
	 * The policy status
	 */
	private PolicyStatus policyStatus;

	/**
	 * The policy objects
	 */
	private List<PolicyObject> policyObjects;
}

package com.askc.insurance.entity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Policy object that holds the insured smaller sub objects (e.g. a House)
 * 
 * @author svkolev
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PolicyObject extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * The object name
	 */
	private String name;

	/**
	 * The sub objects of the object
	 */
	private List<PolicySubObject> policySubObjects;
}

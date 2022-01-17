package com.askc.insurance.entity.enums;

import java.io.Serializable;

/**
 * Policy Risk types
 * 
 * @author svkolev
 *
 */
public enum PolicyRiskType implements Serializable {

	/**
	 * Policy risk type in case of thefts
	 */
	THEFT(1, 0.11, 14, 0.05),
	/**
	 * Policy risk type in case of fire
	 */
	FIRE(2, 0.014, 100, 0.024);

	private final int riskTypeNumber;
	private final double defaultValue;
	private final double borderSum;
	private final double borderValue;

	/**
	 * Risk type constructor.
	 * 
	 * @param riskTypeNumber
	 *            the enumeration number
	 * @param defaultValue
	 *            default coefficient value
	 * @param borderSum
	 *            border sum value after which the <code>borderValue</code> is
	 *            taken as coefficient
	 * @param borderValue
	 *            the border coefficient
	 */
	PolicyRiskType(int riskTypeNumber, double defaultValue, double borderSum, double borderValue) {
		this.riskTypeNumber = riskTypeNumber;
		this.defaultValue = defaultValue;
		this.borderSum = borderSum;
		this.borderValue = borderValue;

	}

	/**
	 * @return the integer value of this risk type.
	 */
	public int riskTypeNumber() {
		return this.riskTypeNumber;
	}

	/**
	 * @return the default coefficient for this risk type
	 */
	public double defaultValue() {
		return this.defaultValue;
	}

	/**
	 * @return the border sum for this risk type
	 */
	public double borderSum() {
		return this.borderSum;
	}

	/**
	 * @return the border coefficient for this risk type
	 */
	public double borderValue() {
		return this.borderValue;
	}

	/**
	 * Get the coefficient for provided insured sum for this risk type
	 * 
	 * @param insuredSum
	 *            the insured sum
	 * @return the coefficient for the insured sum
	 */
	public double getCoefficient(double insuredSum) {
		double result;
		if (insuredSum > borderSum) {
			result = borderValue;
		} else {
			result = defaultValue;
		}
		return result;
	}
}

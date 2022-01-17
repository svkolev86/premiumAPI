package com.askc.insurance.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Base entity that all POJOs should extend
 * 
 * @author svkolev
 *
 */
@Data
@EqualsAndHashCode
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
}

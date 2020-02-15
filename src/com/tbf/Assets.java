package com.tbf;

/**
 * This is an abstract class that models a "generic" asset 
 * that the tbf finacial system manages.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

import java.util.ArrayList;

public abstract class Assets {
	protected String accountCode; 
	protected String assetType;
	protected String label;
	
	/**
	 * This is a constructor that can be called by the subclasses's constructor
	 * and the super(); method.
	 * 
	 * @param accountCode
	 * @param assetType
	 * @param label
	 */
	public Assets(String accountCode, String assetType, String label) {
		this.accountCode = accountCode;
		this.assetType = assetType;
		this.label = label;
	}
	
}





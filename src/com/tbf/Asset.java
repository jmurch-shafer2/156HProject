package com.tbf;

/**
 * This is an abstract class that models a "generic" asset 
 * that the tbf finacial system manages.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

import java.util.ArrayList;

public abstract class Asset {
	protected String accountCode; 
	protected String assetType;
	protected String label;
	
	public Asset(String accountCode, String assetType, String label) {
		this.accountCode = accountCode;
		this.assetType = assetType;
		this.label = label;
	}

	protected abstract String getAssetType();
	
}





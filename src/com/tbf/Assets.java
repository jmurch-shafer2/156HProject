package com.tbf;

/**
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

import java.util.ArrayList;

public abstract class Assets {
	protected String accountCode; 
	protected String assetType;
	protected String label;
	
	
	public Assets(String accountCode, String assetType, String label) {
		this.accountCode = accountCode;
		this.assetType = assetType;
		this.label = label;
	}
	
}





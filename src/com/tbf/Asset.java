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
	protected String assetCode; 
	protected String typeOfAsset;
	protected String label;
	protected int assetId;
	
	public Asset(int assetId,String assetCode, String assetType, String label) {
		this.assetId = assetId;
		this.assetCode = assetCode;
		this.typeOfAsset = assetType;
		this.label = label;
	}


	public int getAssetId() {
		return assetId;
	}

	protected abstract String getAssetType();
	
	public String getLabel() {
		return label;
	}

	public String getAssetCode() {
		return assetCode;
	}
}

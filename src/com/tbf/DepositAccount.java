package com.tbf;

/**
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class DepositAccount extends Assets {
	private double apr;

	public DepositAccount(String accountCode, String assetType, String label, String apr) {
		super(accountCode,assetType,label);
		this.apr = Double.valueOf(apr);
	}
	}
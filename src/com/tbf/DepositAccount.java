package com.tbf;

/**
 * This is a subclass of an Asset that models a real
 * world Deposit Account.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class DepositAccount extends Asset {
	private double apr;
	
	/**
	 * A constructor that creates an instance of a DepositAccount
	 * class.
	 * 
	 * @param accountCode 
	 * @param assetType
	 * @param label
	 * @param apr
	 */
	public DepositAccount(String accountCode, String assetType, String label, String apr) {
		super(accountCode,assetType,label);
		this.apr = Double.valueOf(apr);
	}
	
	public String getAssetType() {
		return "Deposit Account";
	}

	public double getApr() {
		return apr;
	}
	}
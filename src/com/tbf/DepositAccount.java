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
	private double value;
	
	

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
	
	/**
	 * copy constructor
	 * @param that
	 * @param value
	 */
	public DepositAccount(DepositAccount that, double value) {
		super(that.accountCode, that.assetType, that.label);
		this.apr = that.apr;
		this.value = value;
	}
	
	public String getAssetType() {
		return "Deposit Account";
	}
	public double getValue() {
		return value;
	}
	
	/**
	 * Calculates annual return rate
	 * @return
	 */
	public double getReturnRate() {
		return this.getReturn()/this.getValue()*100;
	}

	/**
	 * Calculates annual return
	 * @return
	 */
	public double getReturn() {
		double returnVal = this.getValue() *(Math.exp(this.apr/100) - 1);
		return returnVal;
	}
	
	public double getApr() {
		return apr;
	}
	}
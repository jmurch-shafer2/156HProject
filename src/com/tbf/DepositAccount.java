package com.tbf;

/**
 * This is a subclass of an Asset that models a real world Deposit Account.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class DepositAccount extends Asset {

	private double apr;
	private double value;

	/**
	 * A constructor that creates an instance of a DepositAccount class.
	 * 
	 * @param accountCode
	 * @param assetType
	 * @param label
	 * @param apr
	 */
	public DepositAccount(int assetId, String accountCode, String assetType, String label, String apr) {
		super(assetId, accountCode, assetType, label);
		this.apr = Double.valueOf(apr);
	}

	public DepositAccount(int assetId, String accountCode, String assetType, String label, double apr, double value) {
		super(assetId, accountCode, assetType, label);
		this.apr = apr;
		this.value = value;
	}

	public DepositAccount(int assetId, String accountCode, String assetType, String label, double apr) {
		super(assetId, accountCode, assetType, label);
		this.apr = apr;
	}

	/**
	 * copy constructor
	 * 
	 * @param that
	 * @param value
	 */
	public DepositAccount(DepositAccount that, double value) {
		super(that.assetId, that.assetCode, that.typeOfAsset, that.label);
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
	 * 
	 * @return
	 */
	public double getReturnRate() {
//		return this.getReturn();
		return this.getReturn() / this.getValue() * 100;
	}

	/**
	 * Calculates annual return
	 * 
	 * @return
	 */
	public double getReturn() {
		double returnVal = this.getValue() * (Math.exp(this.apr) - 1);
		return returnVal;
	}

	public double getApr() {
		return apr;
	}
}
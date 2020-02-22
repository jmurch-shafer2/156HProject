package com.tbf;

/**
 * This is a subclass of an Asset that models a real
 * world Private Investment.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class PrivateInvestment extends Asset {
	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double baseOmegaMeasure;
	private double totalValue;
	
	/**
	 * A constructor that creates an instance of the
	 * PrivateInvestments class.
	 * 
	 * @param accountCode
	 * @param assetType
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn
	 * @param baseOmegaMeasure
	 * @param totalValue
	 */
	public PrivateInvestment(String accountCode, String assetType, String label, String quarterlyDividend, String baseRateOfReturn, String baseOmegaMeasure, String totalValue) {
		super(accountCode, assetType, label);
		this.quarterlyDividend = Double.valueOf(quarterlyDividend);
		this.baseRateOfReturn = Double.valueOf(baseRateOfReturn);
		this.baseOmegaMeasure = Double.valueOf(baseOmegaMeasure);
		this.totalValue = Double.valueOf(totalValue);
	}
	
}

package com.tbf;

/**
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class PrivateInvestment extends Assets {
	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double baseOmegaMeasure;
	private double totalValue;
	
	public PrivateInvestment(String accountCode, String assetType, String label, String quarterlyDividend, String baseRateOfReturn, String baseOmegaMeasure, String totalValue) {
		super(accountCode, assetType, label);
		this.quarterlyDividend = Double.valueOf(quarterlyDividend);
		this.baseRateOfReturn = Double.valueOf(baseRateOfReturn);
		this.baseOmegaMeasure = Double.valueOf(baseOmegaMeasure);
		this.totalValue = Double.valueOf(totalValue);
	}
	
}

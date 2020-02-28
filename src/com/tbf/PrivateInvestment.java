package com.tbf;
import java.lang.Math;
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
	private double percentageOwned;
	
	public double getQuarterlyDividend() {
		return quarterlyDividend;
	}

	public double getBaseRateOfReturn() {
		return baseRateOfReturn;
	}

	public double getOmegaMeasure() {
		return baseOmegaMeasure + Math.exp(-125500/this.totalValue);
	}

	public double getTotalValue() {
		return totalValue;
	}
	
	public double getValue() {
		return totalValue * percentageOwned/ 100;
	}
	
	public String getAssetType() {
		return "Private Investment";
	}

	public double getPercentageOwned() {
		return percentageOwned;
	}
	
	
	public double getReturn() {
		double returnVal = ((this.baseRateOfReturn * this.totalValue/100) + (4 * this.quarterlyDividend))* this.percentageOwned/100;
		return returnVal;
	}
	

	public PrivateInvestment(String accountCode, String assetType, String label, String quarterlyDividend, String baseRateOfReturn, String baseOmegaMeasure, String totalValue) {
		super(accountCode, assetType, label);
		this.quarterlyDividend = Double.valueOf(quarterlyDividend);
		this.baseRateOfReturn = Double.valueOf(baseRateOfReturn);
		this.baseOmegaMeasure = Double.valueOf(baseOmegaMeasure);
		this.totalValue = Double.valueOf(totalValue);
	}

	public PrivateInvestment(PrivateInvestment that, double percentageOwned) {
		super(that.accountCode, that.assetType, that.label);
		this.quarterlyDividend = that.quarterlyDividend;
		this.baseRateOfReturn = that.baseRateOfReturn;
		this.baseOmegaMeasure = that.baseOmegaMeasure;
		this.totalValue = that.totalValue;
		this.percentageOwned = percentageOwned;
	}
	
}

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
	
	public PrivateInvestment(int assetId, String accountCode, String assetType, String label, double quarterlyDividend, double baseRateOfReturn, double baseOmegaMeasure, double totalValue, double percentageOwned) {
		super(assetId, accountCode, assetType, label);
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.baseOmegaMeasure = baseOmegaMeasure;
		this.totalValue = totalValue;
		this.percentageOwned = percentageOwned;
	}
	
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
	
	/**
	 * Calculates the owned value of the private investment
	 * 
	 * @return total value of the stock
	 */
	public double getValue() {
		return totalValue * percentageOwned/ 100;
	}
	
	public String getAssetType() {
		return "Private Investment";
	}

	public double getPercentageOwned() {
		return percentageOwned;
	}
	
	/**
	 * Calculates the rate of return for a private investment
	 * @return
	 */
	public double getReturnRate() {
		return this.getReturn()/this.getValue()*100;
	}
	
	/**
	 * Calculates the return for a private investment
	 * 
	 * @return
	 */
	public double getReturn() {
		double returnVal = ((this.baseRateOfReturn * this.totalValue/100) + (4 * this.quarterlyDividend))* this.percentageOwned/100;
		return returnVal;
	}
	
	public PrivateInvestment(int assetId, String accountCode, String assetType, String label, String quarterlyDividend, String baseRateOfReturn, String baseOmegaMeasure, String totalValue) {
		super(assetId, accountCode, assetType, label);
		this.quarterlyDividend = Double.valueOf(quarterlyDividend);
		this.baseRateOfReturn = Double.valueOf(baseRateOfReturn);
		this.baseOmegaMeasure = Double.valueOf(baseOmegaMeasure);
		this.totalValue = Double.valueOf(totalValue);
	}
	
	public PrivateInvestment(int assetId, String accountCode, String assetType, String label, double quarterlyDividend, double baseRateOfReturn, double baseOmegaMeasure, double totalValue) {
		super(assetId, accountCode, assetType, label);
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.baseOmegaMeasure = baseOmegaMeasure;
		this.totalValue = totalValue;
	}
	
	/**
	 * A copy constructor that allows a private investment to be copied with
	 * a percentage owned attribute 
	 * @param that
	 * @param percentageOwned
	 */
	public PrivateInvestment(PrivateInvestment that, double percentageOwned) {
		super(that.assetId, that.assetCode, that.typeOfAsset, that.label);
		this.quarterlyDividend = that.quarterlyDividend;
		this.baseRateOfReturn = that.baseRateOfReturn;
		this.baseOmegaMeasure = that.baseOmegaMeasure;
		this.totalValue = that.totalValue;
		this.percentageOwned = percentageOwned;
	}
	
}

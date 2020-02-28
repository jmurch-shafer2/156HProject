package com.tbf;

/**
 * This is a subclass of an Asset that models a real
 * world Stock.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class Stock extends Asset {
	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double betaMeasure;
	private String stockSymbol;
	private double sharePrice;
	private double sharesOwned;
	
	public Stock(String accountCode, String assetType, String label, String quarterlyDividend,
			String baseRateOfReturn, String betaMeasure, String StockSymbol, String sharePrice) {
		super(accountCode, assetType, label);
		this.quarterlyDividend = Double.valueOf(quarterlyDividend);
		this.baseRateOfReturn = Double.valueOf(baseRateOfReturn);
		this.betaMeasure = Double.valueOf(betaMeasure);
		this.stockSymbol = stockSymbol;
		this.sharePrice = Double.valueOf(sharePrice);
	}
	
	public Stock(Stock that, double sharesOwned) {
		super(that.accountCode, that.assetType, that.label);
		this.quarterlyDividend = that.quarterlyDividend;
		this.baseRateOfReturn = that.baseRateOfReturn;
		this.betaMeasure = that.betaMeasure;
		this.stockSymbol = that.stockSymbol;
		this.sharePrice = that.sharePrice;
		this.sharesOwned = sharesOwned;
	}

	public String getAssetType() {
		return "Stock";
	}

	public double getQuarterlyDividend() {
		return quarterlyDividend;
	}

	public double getBaseRateOfReturn() {
		return baseRateOfReturn;
	}

	public double getBetaMeasure() {
		return betaMeasure;
	}
	
	public double getReturn() {
		double returnVal = ((this.baseRateOfReturn * this.sharePrice/100) + (4 * this.quarterlyDividend)) * this.sharesOwned;
		return returnVal;
	}
	
	public double getValue() {
		return sharePrice * sharesOwned;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public double getSharesOwned() {
		return sharesOwned;
	}
	
	}

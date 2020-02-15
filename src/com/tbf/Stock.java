package com.tbf;

/**
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class Stock extends Assets {
	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double betaMeasure;
	private String stockSymbol;
	private double sharePrice;
	
	public Stock(String accountCode, String assetType, String label, String quarterlyDividend,
			String baseRateOfReturn, String betaMeasure, String StockSymbol, String sharePrice) {
		super(accountCode, assetType, label);
		this.quarterlyDividend = Double.valueOf(quarterlyDividend);
		this.baseRateOfReturn = Double.valueOf(baseRateOfReturn);
		this.betaMeasure = Double.valueOf(betaMeasure);
		this.stockSymbol = stockSymbol;
		this.sharePrice = Double.valueOf(sharePrice);
	}
	
	}

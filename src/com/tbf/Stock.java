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
	
	/**
	 * A constructor that creates an instance of the Stock 
	 * class.
	 * 
	 * @param accountCode
	 * @param assetType
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn
	 * @param betaMeasure
	 * @param StockSymbol
	 * @param sharePrice
	 */
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

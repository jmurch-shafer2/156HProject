package com.tbf;

/**
 * This is a subclass of an Asset that models a real world Stock.
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

	public Stock(int assetId, String accountCode, String assetType, String label, String quarterlyDividend,
			String baseRateOfReturn, String betaMeasure, String StockSymbol, String sharePrice) {
		super(assetId, accountCode, assetType, label);
		this.quarterlyDividend = Double.valueOf(quarterlyDividend);
		this.baseRateOfReturn = Double.valueOf(baseRateOfReturn);
		this.betaMeasure = Double.valueOf(betaMeasure);
		this.stockSymbol = StockSymbol;
		this.sharePrice = Double.valueOf(sharePrice);
	}

	public Stock(int assetId, String accountCode, String assetType, String label, double quarterlyDividend,
			double baseRateOfReturn, double betaMeasure, String StockSymbol, double sharePrice) {
		super(assetId, accountCode, assetType, label);
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.betaMeasure = betaMeasure;
		this.stockSymbol = StockSymbol;
		this.sharePrice = sharePrice;
	}

	public Stock(int assetId, String accountCode, String assetType, String label, double quarterlyDividend,
			double baseRateOfReturn, double betaMeasure, String StockSymbol, double sharePrice, double sharesOwned) {
		super(assetId, accountCode, assetType, label);
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.betaMeasure = betaMeasure;
		this.stockSymbol = StockSymbol;
		this.sharePrice = sharePrice;
		this.sharesOwned = sharesOwned;
	}

	/**
	 * A copy constructor to create and instance of an a stock with the shares owned
	 * attribute.
	 * 
	 * @param Stock
	 * @param sharesOwned
	 */
	public Stock(Stock that, double sharesOwned) {
		super(that.assetId, that.assetCode, that.typeOfAsset, that.label);
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

	/**
	 * Calculates the estimated return
	 * 
	 * @return annual return
	 */
	public double getReturn() {
		double returnVal = ((this.baseRateOfReturn * this.sharePrice/100) + (4 * this.quarterlyDividend))
				* this.sharesOwned;
		return returnVal;
	}

	/**
	 * Calculates the annual return rate
	 * 
	 * @return
	 */
	public double getReturnRate() {
		return this.getReturn() / this.getValue() * 100;
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

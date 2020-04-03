package com.tbf;

/**
 * This is an abstract class that models a "generic" asset 
 * that the tbf finacial system manages.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

import java.util.ArrayList;

public abstract class Asset {
	protected String accountCode; 
	protected String assetType;
	protected String label;
	
	public Asset(String accountCode, String assetType, String label) {
		this.accountCode = accountCode;
		this.assetType = assetType;
		this.label = label;
	}


	public static ArrayList<Asset> getAllAssets() {
		ArrayList<Asset> assets = new ArrayList<Asset>();
		PrivateInvestment pi = null;
		DepositAccount da = null;
		Stock s = null;
		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

		SQLFactory conn = new SQLFactory();

		String query = "SELECT accountCode, assetType, label, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, " +
				"baseOmegaMeasure, totalValue, apr FROM Asset a;";

		conn.startConnection();
		conn.prepareQuery(query);
		conn.runQuery();

		while(conn.next()) {
			if (conn.getString("assetType") == "S") {
				s = new Stock(conn.getString("accountCode"), conn.getString("assetType"), conn.getString("label"),
						conn.getDouble("quarterlyDividend"), conn.getDouble("baseRateOfReturn"), conn.getDouble("betaMeasure"),
						conn.getDouble("stockSymbol"), conn.getDouble("sharePrice"));
				assets.add(s);
			} else if (conn.getString("assetType") == "D") {
				da = new DepositAccount(conn.getString("accountCode"), conn.getString("assetType"), conn.getString("label"), conn.getDouble("apr"));
				assets.add(da);

			} else if (conn.getString("assetType") == "P") {
				pi = new PrivateInvestment(conn.getString("accountCode"), conn.getString("assetType"), conn.getString("label"),
						conn.getDouble("quarterlyDividend"), conn.getDouble("baseRateOfReturn"), conn.getDouble("baseOmegaMeasure"),
						conn.getDouble("totalValue"));
				assets.add(pi);
			}
		}
		conn.endConnection();
		return assets;
	}

	protected abstract String getAssetType();
	
	public String getLabel() {
		return label;
	}

	public String getAccountCode() {
		return accountCode;
	}
}

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
	protected String assetCode; 
	protected String typeOfAsset;
	protected String label;
	protected int assetId;
	
	public Asset(int assetId,String assetCode, String assetType, String label) {
		this.assetId = assetId;
		this.assetCode = assetCode;
		this.typeOfAsset = assetType;
		this.label = label;
	}


	public int getAssetId() {
		return assetId;
	}


	public static ArrayList<Asset> getAllAssets() {
		ArrayList<Asset> assets = new ArrayList<Asset>();
		PrivateInvestment pi = null;
		DepositAccount da = null;
		Stock s = null;

		SQLFactory conn = new SQLFactory();

		String query = "SELECT assetId, assetCode, typeOfAsset, label, quarterlyDividend, baseRateReturn, betaMeasure, stockSymbol, sharePrice, " +
				"baseOmegaMeasure, totalValue, apr FROM Asset a;";

		conn.startConnection();
		conn.prepareQuery(query);
		conn.runQuery();

		while(conn.next()) {
			if (conn.getString("typeOfAsset").equals("S")) {
				s = new Stock(conn.getInt("assetId"),conn.getString("assetCode"), conn.getString("typeOfAsset"), conn.getString("label"),
						conn.getDouble("quarterlyDividend"), conn.getDouble("baseRateReturn"), conn.getDouble("betaMeasure"),
						conn.getString("stockSymbol"), conn.getDouble("sharePrice"));
				
				assets.add(s);
			} else if (conn.getString("typeOfAsset").equals("D")) {
				da = new DepositAccount(conn.getInt("assetId"),conn.getString("assetCode"), conn.getString("typeOfAsset"), conn.getString("label"), conn.getDouble("apr"));
				assets.add(da);
			} else if (conn.getString("typeOfAsset").equals("P")) {
				pi = new PrivateInvestment(conn.getInt("assetId"),conn.getString("assetCode"), conn.getString("typeOfAsset"), conn.getString("label"),
						conn.getDouble("quarterlyDividend"), conn.getDouble("baseRateReturn"), conn.getDouble("baseOmegaMeasure"),
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

	public String getAssetCode() {
		return assetCode;
	}
}

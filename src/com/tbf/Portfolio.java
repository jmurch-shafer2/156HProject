package com.tbf;

import java.util.ArrayList;

/**
 * 
 * @author Joel Murch-Shafer and Natalie Ruckman
 * 
 *         This class models a portfolio for tbf corporation
 */
public class Portfolio {
	private int portfolioId;
	private String portfolioCode;
	private Person owner;
	private Person manager;
	private Person beneficiary;
	private double expectedAnnualReturn;
	private ArrayList<Asset> assetList = new ArrayList<>();

	public Portfolio(int portfolioId, String portfolioCode, Person owner, Person manager, Person beneficiary,
			ArrayList<Asset> assetList) {
		super();
		this.portfolioId = portfolioId;
		this.portfolioCode = portfolioCode;
		this.owner = owner;
		this.manager = manager;
		this.beneficiary = beneficiary;
		this.assetList = assetList;
	}

	/**
	 * Calculates total fees for a portfolio
	 * 
	 * @return
	 */

	public double getTotalFee() {
		if (this.manager.getBrokerState() == "J") {
			return 75.0;
		} else if (this.manager.getBrokerState() == "E") {
			return 0.0;
		} else {
			return 0.0;
		}
	}

	/**
	 * Calculates total commission for a portfolio with a broker
	 * 
	 * @return
	 */
	public double getTotalCommission() {
		if (this.manager.getBrokerState() == "J") {
			double commissionRate = 0.0125;
			return (this.expectedAnnualReturn * commissionRate);
		} else if (this.manager.getBrokerState() == "E") {
			double commissionRate = 0.0375;
			return (this.expectedAnnualReturn * commissionRate);
		} else {
			return 0.0;
		}
	}

	/**
	 * Calculates total risk for a portfolio
	 * 
	 * @param acc
	 * @return
	 */
	public double getRisk(PrivateInvestment acc) {
		return acc.getOmegaMeasure();
	}

	public double getRisk(Stock stonk) {
		return stonk.getBetaMeasure();
	}

	public double getRisk(DepositAccount acc) {
		return 0.0;
	}

	public String getPortfolioCode() {
		return portfolioCode;
	}

	public Person getOwner() {
		return owner;
	}

	public String getOwnerName() {
		return owner.getFirstName() + ", " + owner.getLastName();
	}

	// return manager with an extra check if the manager exists
	public Person getManager() {
		if (manager == null) {
			return owner;
		} else {
			return manager;
		}
	}

	// returns manager name with an extra check if manager doesn't exist
	public String getManagerName() {
		if (manager == null) {
			return "";
		} else {
			return manager.getFirstName() + ", " + manager.getLastName();
		}
	}

	// returns beneficiary with check if a beneficiary is listed
	public Person getBeneficiary() {
		if (beneficiary == null) {
			ArrayList<String> arr = new ArrayList();
			Address add = new Address(0, "", "", "", "", "");
			return new Person(0, "None", "", "", "None", "", add, arr);
		} else {
			return beneficiary;
		}
	}

	public double getExpectedAnnualReturn() {
		return expectedAnnualReturn;
	}

	public ArrayList<Asset> getAssetList() {
		return assetList;
	}

	/**
	 * Calculates aggregate risk of a portfolio
	 * 
	 * @return
	 */
	public double getAggregateRisk() {
		double totalValue = 0;
		for (int i = 0; i < assetList.size(); i++) {
			Asset asset = assetList.get(i);
			String assetType = "";
			assetType = asset.getAssetType();
			if (assetType == "Private Investment") {
				totalValue += ((PrivateInvestment) asset).getValue();
			} else if (assetType == "Deposit Account") {
				totalValue += ((DepositAccount) asset).getValue();
			} else if (assetType == "Stock") {
				totalValue += ((Stock) asset).getValue();
			}
		}
		double aggregateRisk = 0;
		for (int j = 0; j < assetList.size(); j++) {
			double riskFactor = 0;
			Asset asset = assetList.get(j);
			String assetType = asset.getAssetType();
			double value = 0;

			if (assetType == "Private Investment") {
				riskFactor = ((PrivateInvestment) asset).getOmegaMeasure();
				value = ((PrivateInvestment) asset).getValue();
			} else if (assetType == "Deposit Account") {
				riskFactor = 0;
				value = ((DepositAccount) asset).getValue();
			} else if (assetType == "Stock") {
				riskFactor = ((Stock) asset).getBetaMeasure();
				value = ((Stock) asset).getValue();
			}
			aggregateRisk += riskFactor * (value / totalValue);
		}
		return aggregateRisk;
	}

	/**
	 * Calculates total return for a portfolio
	 * 
	 * @return
	 */
	public double getReturn() {
		double totalReturn = 0;
		for (int i = 0; i < assetList.size(); i++) {
			Asset asset = assetList.get(i);
			String assetType = "";
			assetType = asset.getAssetType();
			if (assetType == "Private Investment") {
				totalReturn += ((PrivateInvestment) asset).getReturn();
			} else if (assetType == "Deposit Account") {
				totalReturn += ((DepositAccount) asset).getReturn();
			} else if (assetType == "Stock") {
				totalReturn += ((Stock) asset).getReturn();
			}
		}
		return totalReturn;
	}

	/**
	 * Calculates total commision for a portfolio
	 * 
	 * @return
	 */
	public double getCommision() {
		double brokerCommisionRate;
		if (this.manager.getBrokerState().equals("J")) {
			brokerCommisionRate = 0.0125;
		} else if (this.manager.getBrokerState().equals("E")) {
			brokerCommisionRate = 0.0375;
		} else {
			brokerCommisionRate = 0;
		}
		return this.getReturn() * brokerCommisionRate;
	}

	public double getFees() {
		double totalFees = 0;
		if (this.manager.getBrokerState().equals("J")) {
			totalFees = 75 * this.assetList.size();
		}
		return totalFees;
	}

	/**
	 * Calculates the total value of a portfolio
	 * 
	 * @return
	 */
	public double getTotalValue() {
		double totalVal = 0;

		for (int i = 0; i < assetList.size(); i++) {
			Asset asset = assetList.get(i);
			String assetType = "";
			assetType = asset.getAssetType();
			if (assetType == "Private Investment") {
				totalVal += ((PrivateInvestment) asset).getValue();
			} else if (assetType == "Deposit Account") {
				totalVal += ((DepositAccount) asset).getValue();
			} else if (assetType == "Stock") {

				totalVal += ((Stock) asset).getValue();
			}
		}
		return totalVal;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: " + portfolioId + " \n");
		sb.append("Code: " + portfolioCode + " \n");
		sb.append(this.owner.toString() + " \n");
		sb.append(this.manager.toString() + " \n");
		if (beneficiary != null) {
			sb.append(this.beneficiary.toString() + " \n");
		}
		for (Asset ass : this.assetList) {
			sb.append(ass.toString());
		}
		return sb.toString();
	}

}

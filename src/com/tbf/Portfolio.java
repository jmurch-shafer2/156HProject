package com.tbf;

import java.util.ArrayList;
/** 
 * 
 * @author Joel Murch-Shafer and Natalie Ruckman
 * 
 * This class models a portfolio for tbf corporation
 */
public class Portfolio {
	private String portfolioCode;
	private Person owner;
	private Person manager;
	private Person beneficiary;
	private double expectedAnnualReturn;
	private ArrayList <Asset> assetList = new ArrayList<>();

	public Portfolio(String portfolioCode, Person owner, Person manager, Person beneficiary,
			ArrayList<Asset> assetList) {
		super();
		this.portfolioCode = portfolioCode;
		this.owner = owner;
		this.manager = manager;
		this.beneficiary = beneficiary;
		this.assetList = assetList;
	}
	
	public double getTotalFee() {
		if(this.manager.getBrokerState() == "J") {
			return 75.0;
		}else if(this.manager.getBrokerState() == "E") {
			return 0.0;
		}else {
			return 0.0;
		}
	}

	public double getTotalCommission() {
		if(this.manager.getBrokerState() == "J") {
			double commissionRate = 0.0125;
			return (this.expectedAnnualReturn * commissionRate);
		}else if(this.manager.getBrokerState() == "E") {
			double commissionRate = 0.0375;
			return (this.expectedAnnualReturn * commissionRate);
		}else {
			return 0.0;
		}
	}
	
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

	public Person getManager() {
		return manager;
	}
	public String getManagerName() {
		if(manager == null) {
			return "";
		} else {
			return manager.getFirstName() + ", " + manager.getLastName();
		} 	
	}

	public Person getBeneficiary() {
		return beneficiary;
	}

	public double getExpectedAnnualReturn() {
		return expectedAnnualReturn;
	}

	public ArrayList<Asset> getAssetList() {
		return assetList;
	}
	
	public double getAggregateRisk() {
		double totalValue = 0;
		for(int i=0;i<assetList.size();i++) {
			Asset asset = assetList.get(i);
			String assetType = "";
			assetType = asset.getAssetType();
			if(assetType =="Private Investment") {
				totalValue += ((PrivateInvestment)asset).getValue();
			}else if(assetType =="Deposit Account") {
				totalValue += ((DepositAccount)asset).getValue();
			}else if(assetType =="Stock") {
				totalValue += ((Stock)asset).getValue();
			}else {
				// TODO you dun fucked up
			}	
		}
//		System.out.println(totalValue);
		double aggregateRisk = 0;
		for(int j=0;j<assetList.size();j++) {
			double riskFactor = 0;
			Asset asset = assetList.get(j);
			String assetType = asset.getAssetType();
			double value = 0;
			
			if(assetType =="Private Investment") {
				riskFactor = ((PrivateInvestment)asset).getOmegaMeasure();
				value = ((PrivateInvestment)asset).getValue();
			}else if(assetType =="Deposit Account") {
				riskFactor = 0;
				value = ((DepositAccount)asset).getValue();
			}else if(assetType =="Stock") {
				riskFactor = ((Stock)asset).getBetaMeasure();
				value = ((Stock)asset).getValue();
			}else {
				// TODO you dun fucked up
			}
			aggregateRisk += riskFactor*(value/totalValue);	
		}
		return aggregateRisk;
	}

	
	
	
	
	
	public double getReturn() {
		double totalReturn = 0;
		for(int i=0;i<assetList.size();i++) {
			Asset asset = assetList.get(i);
			String assetType = "";
			assetType = asset.getAssetType();
			if(assetType =="Private Investment") {
				totalReturn += ((PrivateInvestment)asset).getReturn();
			}else if(assetType =="Deposit Account") {
				totalReturn += ((DepositAccount)asset).getReturn();
			}else if(assetType =="Stock") {
				totalReturn += ((Stock)asset).getReturn();
			}else {
				// TODO you dun fucked up
			}	
		}
		return totalReturn;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public double getCommision() {
//		
//	}
//	
//	
//
//	
	public double getFees() {
		double totalFees = 0;
		if(this.manager.getBrokerState().equals("E")) {
			totalFees = 75*this.assetList.size();
		}
		return totalFees;
	}

}

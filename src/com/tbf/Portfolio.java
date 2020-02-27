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
		return acc.getBaseOmegaMeasure();
	}
	public double getRisk(Stock stonk) {
		return stonk.getBetaMeasure();
	}
	public double getRisk(DepositAccount acc) {
		return 0.0;
	}

	
	
//	public double getAggregateRisk() {
//		double sum;
//		for(int i=0;i<assetList.size();i++) {
//			sum += getRisk(this.assetList.get(i));
//		}
//		double aggregateRisk;
//		for(int i=0;i<assetList.size();i++) {
//			aggregate += getRisk(this.assetList.get(i));
//		}
//		
//		return aggregateRisk;
//	}

}

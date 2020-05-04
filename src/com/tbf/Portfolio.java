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
			return manager.getLastName() + ", " + manager.getFirstName();
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

	/**
	 * Used for testing 
	 */
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
	/**
	 * Used for testing 
	 */
	public void print() {
		System.out.println(this.toString());
	}
	
	/**
	 * A functionality class that allows for the creation of reports
	 * Currently has support for generating reports for: Portfolios
	 * 
	 * @param p
	 */
	public static void portfolioShort(Portfolio p) {
		String code = p.getPortfolioCode();
		String owner = p.getOwnerName();
		String manager = p.getManagerName();
		double weightedRisk = p.getAggregateRisk();
		double commisions = p.getCommision();
		double fees = p.getFees();
		double returnVal = p.getReturn();
		double totalVal = p.getTotalValue();
		String str = String.format("%-10.10s %-20.25s %-20.25s $%17.2f   $%17.2f    %17.4f   $%17.2f   $%17.2f",code,owner,manager,fees,commisions,weightedRisk,returnVal,totalVal);
		System.out.println(str);
	}
	
	/**
	 * Takes a list of portfolios and creates a concise report for an executive
	 * @param ArrayList<Portfolio>
	 */
	public static void executiveReport(ArrayList<Portfolio> portfolioList) {
		System.out.println("Portfolio Executive Report\n===========================================================================================================================================================");
		System.out.println(String.format("%-10.10s %-20.25s %-20.25s %-20.15s %-20.15s %-20.15s %-20.15s %-20.15s","Portfolio","Owner","Manager","Fees","Commisions","Weighted Risk","Return","Total"));
		double totalFees= 0;
		double totalCommisions = 0;
		double totalReturn = 0;
		double totalTotal = 0;
		for(Portfolio port:portfolioList) {
			Portfolio.portfolioShort(port);
			totalFees += port.getFees();
			totalCommisions += port.getCommision();
			totalReturn += port.getReturn();
			totalTotal += port.getTotalValue();
		}
		
		System.out.println("                                                     ------------------------------------------------------------------------------------------------------" );
		System.out.println(String.format("%-10.20s %-20.25s %-20.25s $%20.2f$%20.2f %20.4s$%20.2f$%17.2f","","","TOTALS:",totalFees,totalCommisions,"",totalReturn,totalTotal));
	}
	
	/**
	 * Takes a list of portfolios and creates a concise report for an executive
	 * @param SortedLinkedList
	 */
	public static void executiveReport(SortedLinkedList<Portfolio> portfolioList) {
		System.out.println("Portfolio Executive Report\n===========================================================================================================================================================");
		System.out.println(String.format("%-10.10s %-20.25s %-20.25s %-20.15s %-20.15s %-20.15s %-20.15s %-20.15s","Portfolio","Owner","Manager","Fees","Commisions","Weighted Risk","Return","Total"));
		double totalFees= 0;
		double totalCommisions = 0;
		double totalReturn = 0;
		double totalTotal = 0;
		for(Portfolio port:portfolioList) {
			Portfolio.portfolioShort(port);
			totalFees += port.getFees();
			totalCommisions += port.getCommision();
			totalReturn += port.getReturn();
			totalTotal += port.getTotalValue();
		}
		
		System.out.println("                                                     ------------------------------------------------------------------------------------------------------" );
		System.out.println(String.format("%-10.20s %-20.25s %-20.25s $%20.2f$%20.2f %20.4s$%20.2f$%17.2f","","","TOTALS:",totalFees,totalCommisions,"",totalReturn,totalTotal));
	}
	
	
	
	
	
	


	/**
	 * Takes a portfolio as input and creates a detailed report about the portfolio
	 * @param p
	 */
	public static void portfolioLong(Portfolio p) {
		System.out.println("Portfolio " + p.getPortfolioCode() + "\n------------------------------------------");
		System.out.println("Owner:\n" + p.getOwnerName() + "\n" + p.getOwner().getEmailList().toString() + "\n" + p.getOwner().getAddress().toString() + "\n");
		System.out.println("Manager:\n" + p.getManagerName() + "\n");
		System.out.println("Beneficiary:\n" + p.getBeneficiary().getFirstName() + ", " + p.getBeneficiary().getLastName() + "\n" + p.getBeneficiary().getEmailList().toString() + "\n" + p.getBeneficiary().getAddress().toString() + "\n");
		String str = String.format("%-10.10s %15.10s %30s %10.25s  %17s    %13s","Code","Asset","Return Rate","Risk","Annual Return","Value");
		System.out.println(str);
		double totalRisk = p.getAggregateRisk();
		double totalAnnReturn = 0;
		double totalTotal = 0;
		for(Asset a:p.getAssetList()) {
			String assetType = "";
			assetType = a.getAssetType();
			double totalVal = 0;
			double returnRate = 0;
			double risk = 0;
			double annReturn = 0;
			
			if(assetType =="Private Investment") {
				totalVal = ((PrivateInvestment)a).getValue();
				returnRate = ((PrivateInvestment)a).getReturnRate();
				risk = ((PrivateInvestment)a).getOmegaMeasure();
				annReturn = ((PrivateInvestment)a).getReturn();
			}else if(assetType =="Deposit Account") {
				totalVal = ((DepositAccount)a).getValue();
				returnRate = ((DepositAccount)a).getReturnRate();
				risk = 0;
				annReturn = ((DepositAccount)a).getReturn();
			}else if(assetType =="Stock") {
				totalVal = ((Stock)a).getValue();
				returnRate = ((Stock)a).getReturnRate();
				risk = ((Stock)a).getBetaMeasure();
				annReturn = ((Stock)a).getReturn();
			}
			totalAnnReturn += annReturn;
			totalTotal += totalVal;
			String assetStr = String.format("%.10s    %27.27s  %15.2f%%%10.2f   $%15.2f   $%13.2f",a.getAssetCode(),a.getLabel(),returnRate,risk,annReturn,totalVal);
			System.out.println(assetStr);
		}
		System.out.println("                                             -----------------------------------------------------------");
		String totStr = String.format("%-41.10s%16.10s %10.4f$%18.2f$%16.2f","","Totals",totalRisk,totalAnnReturn,totalTotal);
		System.out.println(totStr);
	}
	
	/**
	 * Takes a list of portfolios and generates the detailed list of
	 * each portfolio.
	 * @param portList
	 */
	public static void fullReport(ArrayList<Portfolio> portList) {
		System.out.println("Portfolio Details \n======================================================================================\n");
		
		for(Portfolio p:portList) {
			GenerateReport generate = new GenerateReport();
			generate.portfolioLong(p);
		}
	}

}

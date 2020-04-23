package com.tbf;

import java.util.ArrayList;

public class GenerateReport {
	/**
	 * Takes a portfolio as input and creates a summary of the portfolio
	 * @param p
	 */
	public void portfolioShort(Portfolio p) {
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
	public void executiveReport(ArrayList<Portfolio> portfolioList) {
		System.out.println("Portfolio Executive Report\n===========================================================================================================================================================");
		System.out.println(String.format("%-10.10s %-20.25s %-20.25s %-20.15s %-20.15s %-20.15s %-20.15s %-20.15s","Portfolio","Owner","Manager","Fees","Commisions","Weighted Risk","Return","Total"));
		double totalFees= 0;
		double totalCommisions = 0;
		double totalReturn = 0;
		double totalTotal = 0;
		for(Portfolio port:portfolioList) {
			this.portfolioShort(port);
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
	public void executiveReport(SortedLinkedList<Portfolio> portfolioList) {
		System.out.println("Portfolio Executive Report\n===========================================================================================================================================================");
		System.out.println(String.format("%-10.10s %-20.25s %-20.25s %-20.15s %-20.15s %-20.15s %-20.15s %-20.15s","Portfolio","Owner","Manager","Fees","Commisions","Weighted Risk","Return","Total"));
		double totalFees= 0;
		double totalCommisions = 0;
		double totalReturn = 0;
		double totalTotal = 0;
		for(Portfolio port:portfolioList) {
			this.portfolioShort(port);
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
	public void portfolioLong(Portfolio p) {
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
			String assetStr = String.format("%.10s %36.30s %15.2f%% %10.2f   $%15.2f   $%13.2f",a.getAssetCode(),a.getLabel(),returnRate,risk,annReturn,totalVal);
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
	public void fullReport(ArrayList<Portfolio> portList) {
		System.out.println("Portfolio Details \n======================================================================================\n");
		
		for(Portfolio p:portList) {
			GenerateReport generate = new GenerateReport();
			generate.portfolioLong(p);
		}
	}
	
}

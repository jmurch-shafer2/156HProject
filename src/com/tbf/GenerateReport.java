package com.tbf;

import java.util.ArrayList;

public class GenerateReport {
	public void portfolioShort(Portfolio p) {
		String code = p.getPortfolioCode();
		String owner = p.getOwnerName();
		String manager = p.getManagerName();
		double weightedRisk = p.getAggregateRisk();
		String str = String.format("%-10.10s %-20.25s %-20.25s $%17.2f   $%17.2f    %17.4f   $%17.2f   $%17.2f",code,owner,manager,0.0,0.0,weightedRisk,0.0,0.0);
		System.out.println(str);
	}
	public void executiveReport(ArrayList<Portfolio> portfolioList) {
		System.out.println("Portfolio Executive Report\n==========================================================================================================================================================");
		System.out.println(String.format("%-10.10s %-20.25s %-20.25s %-20.15s %-20.15s %-20.15s %-20.15s %-20.15s","Portfolio","Owner","Manager","Fees","Commisions","Weighted Risk","Return","Total"));
		double totalFees;
		double totalCommisions;
		double totalReturn;
		double totalTotal;
		for(Portfolio port:portfolioList) {
			this.portfolioShort(port);
//			totalFees += ;
//			totalCommisions += ;
//			totalReturn += ;
//			totalTotal += ;
			
			
		}
	}
		
	
	
}

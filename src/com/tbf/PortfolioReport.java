package com.tbf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PortfolioReport {
	public static void main(String[] argv) {

		ArrayList<Person> listOfPeople = Person.getAllPeople();

		System.out.println(listOfPeople.toString());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		ArrayList<Asset> listOfAssets = new ArrayList();
//
//		for (;;) {
//
//			Asset localAsset = new Stock();
//			listOfAssets.add(localAsset);
//		}
//
//		ArrayList<Portfolio> listOfPortfolios = new ArrayList();
//
//		for (;;) {
//
//			Portfolio localPortfolio = new Portfolio();
//			listOfPortfolios.add(localPortfolio);
//		}
//
//		// Sorts the list of portfolios into lexogrphic order
//		Collections.sort(listOfPortfolios, new Comparator<Portfolio>() {
//			public int compare(Portfolio port, Portfolio that) {
//				return port.getOwnerName().compareTo(that.getOwnerName());
//			}
//		});
//		// Sorts assets in each portfolio into lexiagraphic order
//		for (Portfolio port : listOfPortfolios) {
//			Collections.sort(port.getAssetList(), new Comparator<Asset>() {
//				public int compare(Asset ass, Asset crack) {
//					return ass.getLabel().compareTo(crack.getLabel());
//				}
//			});
//		}
//
//		// Generates executive report and then the detailed report
//		GenerateReport gen = new GenerateReport();
//		gen.executiveReport(listOfPortfolios);
//		System.out.println("\n\n\n");
//		gen.fullReport(listOfPortfolios);

	}
}

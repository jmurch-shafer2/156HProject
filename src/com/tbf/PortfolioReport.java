package com.tbf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;



/**
 * calls functions to produce and print portfolio report. Also uses log4j as a
 * logging system.
 * 
 * @return ArrayList of all people
 */

public class PortfolioReport {
	public static void main(String[] argv) {

		Logger log = Logger.getLogger(PortfolioReport.class);
		BasicConfigurator.configure();

		log.info("Loading all people, assets, and portfolios.");
		ArrayList<Person> listOfPeople = DataLoader.getAllPeople();

		ArrayList<Asset> listOfAsset = DataLoader.getAllAssets();

		ArrayList<Portfolio> listOfPortfolios = DataLoader.getAllPortfolios(listOfAsset, listOfPeople);

//		log.info("Sorting portfolios and assets");
//		// Sorts the list of portfolios into lexogrphic order
//		Collections.sort(listOfPortfolios, new Comparator<Portfolio>() {
//			public int compare(Portfolio port, Portfolio that) {
//				return port.getOwnerName().compareTo(that.getOwnerName());
//			}
//		});
//
//		// Sorts assets in each portfolio into lexiagraphic order
//		for (Portfolio port : listOfPortfolios) {
//			Collections.sort(port.getAssetList(), new Comparator<Asset>() {
//				public int compare(Asset ass, Asset crack) {
//					return ass.getLabel().compareTo(crack.getLabel());
//				}
//			});
//		}

		log.info("Generating Report");
		// Generates executive report and then the detailed report
		GenerateReport gen = new GenerateReport();
		gen.executiveReport(listOfPortfolios);
		System.out.println("\n\n\n");
		gen.fullReport(listOfPortfolios);

//		PortfolioData.removeAllPortfolios();
//		PortfolioData.removeAllPersons();
//		PortfolioData.removeAllAssets();
		
//		PortfolioData.addEmail("02a","Johnjapplesauce@yahhhhhhh.com");
//		PortfolioData.removePerson("02a");
//		PortfolioData.removeAsset("003");
//		try {
//			PortfolioData.addPerson("code", "first", "last", "street", "city", "state", "68008", "country", "brokerType", "secBrokerId");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		PortfolioData.removeAsset("003");
//		
//		try {
//			PortfolioData.addDepositAccount("003","3 year CD",0.31);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			PortfolioData.addPrivateInvestment("codename 007", "the mighty private account", 12.3, 12.4, 3.0, 6.0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			PortfolioData.addStock("stank", "stanky label", 12.11111, 18.5, 11.1, "STAK", 88.88);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
////		PortfolioData.removePortfolio("010");
//		
//		PortfolioData.addPortfolio("portCode", "02a", "014", null);		
//		
//		try {
//			PortfolioData.addAsset("portCode", "003", 12.2);
//			PortfolioData.addAsset("portCode", "codename 007", 12.2);
//			PortfolioData.addAsset("portCode", "stank", 12.2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		System.out.println("done");
	}
}

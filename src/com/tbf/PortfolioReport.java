package com.tbf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.apache.log4j.*;

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

		log.info("Sorting portfolios and assets");
		// Sorts the list of portfolios into lexogrphic order
		Collections.sort(listOfPortfolios, new Comparator<Portfolio>() {
			public int compare(Portfolio port, Portfolio that) {
				return port.getOwnerName().compareTo(that.getOwnerName());
			}
		});

		// Sorts assets in each portfolio into lexiagraphic order
		for (Portfolio port : listOfPortfolios) {
			Collections.sort(port.getAssetList(), new Comparator<Asset>() {
				public int compare(Asset ass, Asset crack) {
					return ass.getLabel().compareTo(crack.getLabel());
				}
			});
		}

		log.info("Generating Report");
		// Generates executive report and then the detailed report
		GenerateReport gen = new GenerateReport();
		gen.executiveReport(listOfPortfolios);
		System.out.println("\n\n\n");
		gen.fullReport(listOfPortfolios);

		PortfolioData.removeAllPortfolios();
		PortfolioData.removeAllPersons();
		System.out.println("done");
	}
}

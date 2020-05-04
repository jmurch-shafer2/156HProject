package com.tbf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class generates an executive report followed by a detailed report of all
 * assets managed by tbf banking.
 * 
 * @author Joel Murch-Shafer and Natalie Ruckman
 *
 */
public class PortfolioReport {
	public static void main(String[] argv) {
		ArrayList<Person> listOfPeople = DataFromFiles.peopleListFromFile("data//Persons.dat");
		ArrayList<Asset> listOfAssets = DataFromFiles.assetsListFromFile("data//Assets.dat");
		ArrayList<Portfolio> listOfPortfolios = DataFromFiles.portfolioListFromFile("data//Portfolios.dat",
				listOfPeople, listOfAssets);

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

		// Generates executive report and then the detailed report
		Portfolio.executiveReport(listOfPortfolios);
		System.out.println("\n\n\n");
		Portfolio.fullReport(listOfPortfolios);
	}
}

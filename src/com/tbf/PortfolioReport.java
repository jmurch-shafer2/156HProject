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

		GenerateReport gen = new GenerateReport();

		Comparator<Portfolio> byOwnerName = new Comparator<>() {
			@Override
			public int compare(Portfolio one, Portfolio two) {
				if (one.getOwner().getLastName().compareTo(two.getOwner().getLastName()) > 0) {
					return 1;
				} else if (one.getOwner().getLastName().compareTo(two.getOwner().getLastName()) < 0) {
					return -1;
				} else {
					if (one.getOwner().getFirstName().compareTo(two.getOwner().getFirstName()) > 0) {
						return 1;
					} else if (one.getOwner().getFirstName().compareTo(two.getOwner().getFirstName()) < 0) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		};

		SortedLinkedList<Portfolio> sortedByOwnerName = new SortedLinkedList<Portfolio>(byOwnerName);
		for (Portfolio port : listOfPortfolios) {
			sortedByOwnerName.addToList(port);
		}
		
		log.info("Generating Report Sorted By Owner Name");
		// Generates executive report and then the detailed report
		gen.executiveReport(sortedByOwnerName);

		System.out.println("\n\n");

		Comparator<Portfolio> byValue = new Comparator<>() {
			@Override
			public int compare(Portfolio one, Portfolio two) {
				if (one.getTotalValue() > two.getTotalValue()) {
					return -1;
				} else if (one.getTotalValue() < two.getTotalValue()) {
					return 1;
				} else {
					return 0;
				}
			}
		};

		SortedLinkedList<Portfolio> sortedByValue = new SortedLinkedList<Portfolio>(byValue);
		for (Portfolio port : listOfPortfolios) {
			sortedByValue.addToList(port);
		}
		
		log.info("Generating Report Sorted By Value");
		// Generates executive report and then the detailed report
		gen.executiveReport(sortedByValue);

		System.out.println("\n\n");

		Comparator<Portfolio> byManagerName = new Comparator<>() {
			@Override
			public int compare(Portfolio one, Portfolio two) {
				if (one.getManager().getLastName().compareTo(two.getManager().getLastName()) > 0) {
					return 1;
				} else if (one.getManager().getLastName().compareTo(two.getManager().getLastName()) < 0) {
					return -1;
				} else {
					if (one.getManager().getFirstName().compareTo(two.getManager().getFirstName()) > 0) {
						return 1;
					} else if (one.getManager().getFirstName().compareTo(two.getManager().getFirstName()) < 0) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		};

		Comparator<Portfolio> byManager = new Comparator<>() {
			@Override
			public int compare(Portfolio one, Portfolio two) {
				if (one.getManager().getBrokerState().equals("E") && two.getManager().getBrokerState().equals("E")) {
					return byManagerName.compare(one, two);
				} else if (one.getManager().getBrokerState().equals("E")
						|| two.getManager().getBrokerState().equals("E")) {
					if (one.getManager().getBrokerState().equals("E")) {
						return -1;
					} else {
						return 1;
					}
				} else if (one.getManager().getBrokerState().equals("J")
						&& two.getManager().getBrokerState().equals("J")) {
					return byManagerName.compare(one, two);
				} else if (one.getManager().getBrokerState().equals("J")
						|| two.getManager().getBrokerState().equals("J")) {
					if (one.getManager().getBrokerState().equals("J")) {
						return -1;
					} else {
						return 1;
					}
				} else {
					return byManagerName.compare(one, two);
				}
			}
		};

		SortedLinkedList<Portfolio> sortedByManager = new SortedLinkedList<Portfolio>(byManager);
		for (Portfolio port : listOfPortfolios) {
			sortedByManager.addToList(port);
		}

		log.info("Generating Report Sorted By Manager");
		// Generates executive report and then the detailed report
		gen.executiveReport(sortedByManager);

	}
}

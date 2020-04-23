package com.tbf;

import java.util.ArrayList;
import java.util.Comparator;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class TestingDriver {
	public static void main(String[] argv) {
//		Assignment 5
//		System.out.println("Address");
//		Address add = Address.getAddress(1);
//		System.out.println(add.toString() + "\n");
//	
//		System.out.println("Person");
//		Person perp = Person.getPerson(3);
//		System.out.println(perp.toString() + "\n");
//		
//		System.out.println("Portfolios");
//		Portfolio port = Portfolio.getPortfolio(2);
//		System.out.println(port.toString() + "\n");

//		Assignment 6
		
		Logger log = Logger.getLogger(PortfolioReport.class);
		BasicConfigurator.configure();
		
//		PortfolioData.removeAllPortfolios();
//		PortfolioData.removeAllPersons();
//		PortfolioData.removeAllAssets();
		
		
		PortfolioData.addAsset("000", "003", 100000000);
		System.out.println("done");
		
		
		

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
//		sortedByOwnerName.print();
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
//		sortedByValue.print();
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
				} else if (one.getManager().getBrokerState().equals("E") || two.getManager().getBrokerState().equals("E")) {
					if (one.getManager().getBrokerState().equals("E")) {
						return -1;
					} else {
						return 1;
					}
				} else if (one.getManager().getBrokerState().equals("J") && two.getManager().getBrokerState().equals("J")) {
					return byManagerName.compare(one, two);
				} else if (one.getManager().getBrokerState().equals("J") || two.getManager().getBrokerState().equals("J")) {
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
//		sortedByManager.print();
		
		log.info("Generating Report Sorted By Manager");
		// Generates executive report and then the detailed report
		gen.executiveReport(sortedByManager);
		

	}
}

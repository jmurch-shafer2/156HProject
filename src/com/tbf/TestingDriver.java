package com.tbf;

import java.util.ArrayList;

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
//		PortfolioData.removeAllPersons();
//		System.out.println("done");
		ArrayList<Person> listOfPeople = DataLoader.getAllPeople();

		ArrayList<Asset> listOfAsset = DataLoader.getAllAssets();

		ArrayList<Portfolio> listOfPortfolios = DataLoader.getAllPortfolios(listOfAsset, listOfPeople);
		
		
		ByOwnerName comp = new ByOwnerName();
		SortedLinkedList <Portfolio> test = new SortedLinkedList<Portfolio>(comp);
		for(Portfolio port: listOfPortfolios) {
			test.addToList(port);
		}
		test.print();
		
		
	}
}

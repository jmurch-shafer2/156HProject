package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class PortfolioReportOLDDDD {
	public static void main(String[] argv) {
//		Loads a file where People objects will be created
		String personsPath = "data//Persons.dat";
		Scanner scan = null;
		ArrayList<Person> listOfPeople = new ArrayList();

//		Implementing a try, catch, finally to safely read from the file and check for a file not found exception
		ArrayList<String> linesOfPeople = new ArrayList();
		try {
			File personsFilePath = new File(personsPath);
			scan = new Scanner(personsFilePath);
			String numberOfLines = scan.nextLine();
//	  		Reads the Persons.dat and stores each line in an iterable array
			while (scan.hasNextLine()) {
				String tempLine = scan.nextLine();
				linesOfPeople.add(tempLine);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if (scan != null)
				scan.close();
		}
		for (String line : linesOfPeople) {
			// Initialize all of the variables
			String localPersonCode = "";
			String brokerData = "";
			String localBrokerState = "";
			String localSECIdentifier = "";
			String fullName = "";
			String localFirstName = "";
			String localLastName = "";
			String localAddress = "";
			ArrayList<String> localEmailList = new ArrayList<>();
			String[] tokens = line.split(";", -1);
			localPersonCode = tokens[0];
			brokerData = tokens[1];
			fullName = tokens[2];
			localAddress = tokens[3];
			String emailAddressesString = tokens[4];
			// Separating broker data out
			String[] arrBroker = brokerData.split(",", -1);
			if (arrBroker.length == 2) {
				localBrokerState = arrBroker[0];
				localSECIdentifier = arrBroker[1];
			}
			String[] arrNames = fullName.split(",", -1);
			localFirstName = arrNames[0];
			localLastName = arrNames[1];
			String[] arrEmails = emailAddressesString.split(",", -1);
			for (int i = 0; i < arrEmails.length; i++) {
				localEmailList.add(arrEmails[i]);
			}
			Address addressTemp = null;
			// Creating an instance of an address
			String[] addressTokens = localAddress.split(",", -1);
			String streetTemp = addressTokens[0];
			String cityTemp = addressTokens[1];
			String stateTemp = addressTokens[2];
			String zipcodeTemp = addressTokens[3];
			String countryTemp = addressTokens[4];
			addressTemp = new Address(streetTemp, cityTemp, stateTemp, countryTemp, zipcodeTemp);
			// Create an instance of a person
			Person localPerson = new Person(localPersonCode, localBrokerState, localSECIdentifier, localFirstName,
					localLastName, addressTemp, localEmailList);
			// Appends each person to an array list of people
			listOfPeople.add(localPerson);
		}

		// Loads a file where Asset objects will be created
		String assetsPath = "data//Assets.dat";
		// Implementing a try, catch, finally to safely read from the file and check for
		// a file not found exception
		ArrayList<Asset> listOfAssets = new ArrayList();
		ArrayList<String> linesOfAssets = new ArrayList();
		try {
			File assetFilePath = new File(assetsPath);
			scan = new Scanner(assetFilePath);
			String numberOfLines = scan.nextLine();

			// Reads the Assets.dat and stores each line in an iterable array
			while (scan.hasNextLine()) {
				String tempLine = scan.nextLine();
				linesOfAssets.add(tempLine);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if (scan != null)
				scan.close();
		}
		for (String line : linesOfAssets) {
//	        Splits each line 
			String[] tokens = line.split(";", -1);
			String accountCode = tokens[0];
			String assetType = tokens[1];
			String label = tokens[2];
//	    	Checks the type of asset and instantiates the corresponding object
			if (assetType.equals("D")) {
//	        	Creates a deposit account object from the tokenized line
				String apr = tokens[3];
				DepositAccount holdingAsset = new DepositAccount(accountCode, assetType, label, apr);
				listOfAssets.add(holdingAsset);
			} else if (assetType.equals("S")) {
//	        	Creates a stock object from the tokenized line

				String quarterlyDividend = tokens[3];
				String baseRateOfReturn = tokens[4];
				String betaMeasure = tokens[5];
				String stockSymbol = tokens[6];
				String sharePrice = tokens[7];
				Stock holdingAsset = new Stock(accountCode, assetType, label, quarterlyDividend, baseRateOfReturn,
						betaMeasure, stockSymbol, sharePrice);
				listOfAssets.add(holdingAsset);
			} else if (assetType.equals("P")) {
//	        	Creates a private investment object from the tolkenized line
				String quarterlyDividend = tokens[3];
				String baseRateOfReturn = tokens[4];
				String baseOmegaMeasure = tokens[5];
				String totalValue = tokens[6];
				PrivateInvestment holdingAsset = new PrivateInvestment(accountCode, assetType, label, quarterlyDividend,
						baseRateOfReturn, baseOmegaMeasure, totalValue);
				listOfAssets.add(holdingAsset);
			} else {
				System.out.println(
						"There is an asset that does not have a specified type. This asset will not be created.");
			}
		}

		String portfolioPath = "data//Portfolios.dat";
		// Implementing a try, catch, finally to safely read from the file and check for
		// a file not found exception
		ArrayList<Portfolio> listOfPortfolios = new ArrayList();
		ArrayList<String> linesOfPortfolios = new ArrayList();
		try {
			File portfolioFilePath = new File(portfolioPath);
			scan = new Scanner(portfolioFilePath);
			String numberOfLines = scan.nextLine();

			// Reads the Assets.dat and stores each line in an iterable array
			while (scan.hasNextLine()) {
				String tempLine = scan.nextLine();
				linesOfPortfolios.add(tempLine);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if (scan != null)
				scan.close();
		}

		// Iterating through the array of lines from the file to create an array of
		// portfolios
		for (String line : linesOfPortfolios) {
			String[] tokens = line.split(";", -1);
			if (tokens.length == 5) {

				String portfolioCode = tokens[0];
				String ownerCode = tokens[1];
				String managerCode = tokens[2];
				String beneficiaryCode = tokens[3];
				String assetList = tokens[4];
				ArrayList<Asset> localAssetList = new ArrayList();

				String[] assetTokens = assetList.split(",");
				if (!assetTokens[0].equals("")) {
					for (int i = 0; i < assetTokens.length; i++) {
						String[] asset = assetTokens[i].split(":");
						String identifier = asset[0];
						double value = Double.valueOf(asset[1]);

						/*
						 * Iterates through a list of assets to find each asset referenced in the
						 * portfolio to an actual object and then makes a copy of the object that the
						 * portfolio can "own."
						 */
						for (Asset thing : listOfAssets) {
							String assetType = thing.getAssetType();
							if (assetType == "Private Investment") {
								if (identifier.equals(thing.getAccountCode())) {
									PrivateInvestment tempAsset = new PrivateInvestment((PrivateInvestment) thing,
											value);
									localAssetList.add(tempAsset);
								}
							} else if (assetType == "Deposit Account") {
								if (identifier.equals(thing.getAccountCode())) {
									DepositAccount tempAsset = new DepositAccount((DepositAccount) thing, value);
									localAssetList.add(tempAsset);
								}
							} else if (assetType == "Stock") {
								if (identifier.equals(thing.getAccountCode())) {
									Stock tempAsset = new Stock((Stock) thing, value);
									localAssetList.add(tempAsset);
								}
							}
						}
					}

				}
				Person owner = null;
				Person manager = null;
				Person beneficiary = null;

				/*
				 * Iterates though a list of people to find each person refrenced in the
				 * portfolio and make a copy of the person object that the portfolio can own
				 */
				for (Person loopPerson : listOfPeople) {
					String personCode = loopPerson.getPersonCode();
					if (personCode.equals(ownerCode)) {
						owner = new Person(loopPerson);
					} else if (personCode.equals(managerCode)) {
						manager = new Person(loopPerson);
					} else if (personCode.equals(beneficiaryCode)) {
						beneficiary = new Person(loopPerson);
					}
				}
				if (manager == null) {
					manager = owner;
					// The owner of the portfolio is also the manager is another manager does not
					// exist for the portfolio
				}
				Portfolio localPortfolio = new Portfolio(portfolioCode, owner, manager, beneficiary, localAssetList);
				listOfPortfolios.add(localPortfolio);
			}
		}

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
		GenerateReport gen = new GenerateReport();
		gen.executiveReport(listOfPortfolios);
		System.out.println("\n\n\n");
		gen.fullReport(listOfPortfolios);

	}
}

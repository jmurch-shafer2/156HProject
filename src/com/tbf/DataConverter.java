package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.tbf.Asset;
import com.tbf.Person;
import com.tbf.JSONConversion;
import com.tbf.XMLConversion;

/**
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 * This program takes input database files with
 * Asset information and people information for the
 * tbf banking system. This program uses OOP to create 
 * a smart data structure. This program currently takes 
 * input files from the /data folder and serializes them into 
 * XML and JSON files that are saved in the data folder.
 * 
 * @see The Xstream library for serializing and deserializing
 * XML and JSON objects. 
 */

public class DataConverter {
	public static void main(String[] argc) throws IOException {
		
//		Loads a file where People objects will be created
		String personsPath = "data//inputOutputExamples//Persons.dat";
		Scanner scan = null;
		ArrayList <Person> listOfPeople = new ArrayList();
//		Implementing a try, catch, finally to safely read from the file and check for a file not found exception
		
		ArrayList <String> linesOfPeople = new ArrayList();
		try {
			File personsFilePath = new File(personsPath);
		    scan = new Scanner(personsFilePath);
		    String numberOfLines = scan.nextLine();
		    
//		    Reads the Persons.dat and stores each line in an iterable array
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
		
		for(String line: linesOfPeople) {
			System.out.println(line);
//			Initialize all of the variables
			String localPersonCode = "";
			String brokerData = "";
			String localBrokerState = "";
			String localSECIdentifier = "";
			String fullName = "";
	        String localFirstName = "";
			String localLastName = "";
	        String localAddress = "";
	        ArrayList<String> localEmailList= new ArrayList<>();	
			
	        String[] tokens = line.split(";",-1);
			if (tokens.length == 5) {
				localPersonCode = tokens[0];
				brokerData = tokens[1];
				fullName = tokens[2];
				localAddress = tokens[3];
				String emailAddressesString = tokens[4];
				
				
				//Separating broker data out
				String[] arrBroker = brokerData.split(",",-1);
				if(arrBroker.length == 2) {
					localBrokerState = arrBroker[0];
					localSECIdentifier = arrBroker[1];
				}
				
				String[] arrNames = fullName.split(",",-1);
				localFirstName = arrNames[0];
				localLastName = arrNames[1];
				
				String[] arrEmails = emailAddressesString.split(",",-1);
				for(int i = 0;i< arrEmails.length;i++) {
					localEmailList.add(arrEmails[i]);
				}
			}
	        
//			Creating an instance of an address
			String[] addressTokens = localAddress.split(",",-1);
			String streetTemp = addressTokens[0];
			String cityTemp = addressTokens[1];
			String stateTemp = addressTokens[2];
			String zipcodeTemp = addressTokens[3];
			String countryTemp = addressTokens[4];
			Address addressTemp = new Address(streetTemp, cityTemp, stateTemp, countryTemp, zipcodeTemp);
			
//	        Create an instance of a person 			
			Person localPerson = new Person(localPersonCode, localBrokerState, localSECIdentifier, localFirstName, localLastName,
					addressTemp, localEmailList);

//	        Appends each person to an array list of people
	        listOfPeople.add(localPerson);
	    }

			
		
		
		
		
	    
        
        
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		Loads a file where Asset objects will be created
		String assetsPath = "data//Assets.dat";
		Scanner assetScan = null;
		ArrayList <Asset> listOfAssets = new ArrayList();
		
//		Implementing a try, catch, finally to safely read from the file and check for a file not found exception
		try {
			File assetsFilePath = new File(assetsPath);
			assetScan = new Scanner(assetsFilePath);
		    String numberOfLines = assetScan.nextLine();
		    
//		    Iterates through the file line by line
		    while (assetScan.hasNextLine()) {
		        String assetLine = assetScan.nextLine();
		        
//		        Splits each line 
		        String[] tokens = assetLine.split(";",-1);
		        String accountCode = tokens[0]; 
		    	String assetType = tokens[1];
		    	String label = tokens[2];
		    	
//		    	Checks the type of asset and instantiates the corresponding object
		        if (assetType.equals("D")) {
		        	
//		        	Creates a deposit account object from the tokenized line
		        	String apr = tokens[3];
		        	DepositAccount holdingAsset = new DepositAccount(accountCode, assetType, label, apr);
		        	listOfAssets.add(holdingAsset);
		        
		        } else if (assetType.equals("S")) {
		        	
//		        	Creates a stock object from the tokenized line
		        	String quarterlyDividend = tokens[3];
		        	String baseRateOfReturn = tokens[4];
		        	String betaMeasure = tokens[5];
		        	String stockSymbol = tokens[6];
		        	String sharePrice = tokens[7];
		        	Stock holdingAsset = new Stock(accountCode, assetType, label, quarterlyDividend, 
		        			baseRateOfReturn, betaMeasure, stockSymbol, sharePrice);
		        	listOfAssets.add(holdingAsset);
		        
		        } else if (assetType.equals("P")) {
		        	
//		        	Creates a private investment object from the tolkenized line
		        	String quarterlyDividend = tokens[3];
		        	String baseRateOfReturn = tokens[4];
		        	String baseOmegaMeasure = tokens[5];
		        	String totalValue = tokens[6];
		        	PrivateInvestment holdingAsset = new PrivateInvestment(accountCode, assetType, label, 
		        			quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue);
		        	listOfAssets.add(holdingAsset);
		        
		        } else {
		        	System.out.println("There is an asset that does not have a specified type. This asset will not be created.");
		        }
		    }
		} catch (FileNotFoundException e) {
		    System.out.println(e.getMessage());
		} finally {
		    if (assetScan != null)
		    	assetScan.close();
		}
		
//		Creating XML and JSON objects, then saving them to a file in /data
		
		XMLConversion xml = new XMLConversion();
		xml.arrPeopleToXML(listOfPeople,"data//Persons.xml");
		xml.arrAssetsToXML(listOfAssets,"data//Assets.xml");
		
		JSONConversion json = new JSONConversion();
		json.arrPeopleToJSON(listOfPeople,"data//Persons.json");		
		json.arrAssetsToJSON(listOfAssets,"data//Assets.json");

	}
}
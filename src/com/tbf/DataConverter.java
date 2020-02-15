package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.tbf.Assets;
import com.tbf.Person;
import com.tbf.JSONConversions;
import com.tbf.XMLConversions;

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
		String personsPath = "data//Persons.dat";
		Scanner scan = null;
		ArrayList <Person> listOfPeople = new ArrayList();
//		Implementing a try, catch, finally to safely read from the file and check for a file not found exception
		try {
			File personsFilePath = new File(personsPath);
		    scan = new Scanner(personsFilePath);
		    String numberOfLines = scan.nextLine();
		    
//		    Reads the Persons.dat line by line
		    while (scan.hasNextLine()) {
		        String line = scan.nextLine();
		        
//		        Create an instance of a person for each line in Persons.dat
		        Person localPerson = new Person(line);
		        
//		        Appends each person to an array list of people
		        listOfPeople.add(localPerson);
		    }
		} catch (FileNotFoundException e) {
		    System.out.println(e.getMessage());
		} finally {
		    if (scan != null)
		        scan.close();
		}
		
//		Loads a file where Asset objects will be created
		String assetsPath = "data//Assets.dat";
		Scanner assetScan = null;
		ArrayList <Assets> listOfAssets = new ArrayList();
		
//		Implementing a try, catch, finally to safely read from the file and check for a file not found exception
		try {
			File assetsFilePath = new File(assetsPath);
			assetScan = new Scanner(assetsFilePath);
		    String numberOfLines = assetScan.nextLine();
		    
//		    Iterates through the file line by line
		    while (assetScan.hasNextLine()) {
		        String line = assetScan.nextLine();
		        
//		        Splits each line 
		        String[] tokens = line.split(";",-1);
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
		
		XMLConversions xml = new XMLConversions();
		xml.arrPeopleToXML(listOfPeople,"data//Persons.xml");
		xml.arrAssetsToXML(listOfAssets,"data//Assets.xml");
		
		JSONConversions json = new JSONConversions();
		json.arrPeopleToJSON(listOfPeople,"data//Persons.json");		
		json.arrAssetsToJSON(listOfAssets,"data//Assets.json");

	}
}
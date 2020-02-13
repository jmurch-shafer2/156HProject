package cse.unl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import cse.unl.*; //TODO make this not like this
/**
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */


//TODO get rid of redundant asset type 

public class DataConversions {
	public static void main(String[] argc) {
		
		//Look at people
		String personsPath = "data//inputOutputExamples//inputOutputExamples//Persons.dat";
		Scanner scan = null;
		ArrayList <Person> listOfPeople = new ArrayList();
		try {
			File personsFilePath = new File(personsPath);
		    scan = new Scanner(personsFilePath);
		    String numberOfLines = scan.nextLine();
		    while (scan.hasNextLine()) {
		        String line = scan.nextLine();
//		        System.out.println(line);
		        Person localPerson = new Person(line);
		        listOfPeople.add(localPerson);
		    }
		} catch (FileNotFoundException e) {
		    System.out.println(e.getMessage());
		} finally {
		    if (scan != null)
		        scan.close();
		}
		
		//look at assets
		String assetsPath = "data//inputOutputExamples//inputOutputExamples//Assets.dat";
		Scanner assetScan = null;
		ArrayList <Assets> listOfAssets = new ArrayList();
		try {
			File assetsFilePath = new File(assetsPath);
			assetScan = new Scanner(assetsFilePath);
		    String numberOfLines = assetScan.nextLine();
		    while (assetScan.hasNextLine()) {
		        String line = assetScan.nextLine();
//		        System.out.println(line);
		        String[] tokens = line.split(";");
		        String accountCode = tokens[0]; 
		    	String assetType = tokens[1];
		    	String label = tokens[2];
		    	
		        if (assetType.equals("D")) {
		        	String apr = tokens[3];
		        	DepositAccount holdingAsset = new DepositAccount(accountCode, assetType, label, apr);
		        	listOfAssets.add(holdingAsset);
		        
		        } else if (assetType.equals("S")) {
		        	String quarterlyDividend = tokens[3];
		        	String baseRateOfReturn = tokens[4];
		        	String betaMeasure = tokens[5];
		        	String stockSymbol = tokens[6];
		        	String sharePrice = tokens[7];
		        	Stock holdingAsset = new Stock(accountCode, assetType, label, quarterlyDividend, 
		        			baseRateOfReturn, betaMeasure, stockSymbol, sharePrice);
		        	listOfAssets.add(holdingAsset);
		        
		        } else if (assetType.equals("P")) {
		        	String quarterlyDividend = tokens[3];
		        	String baseRateOfReturn = tokens[4];
		        	String baseOmegaMeasure = tokens[5];
		        	String totalValue = tokens[6];
		        	PrivateInvestment holdingAsset = new PrivateInvestment(accountCode, assetType, label, 
		        			quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue);
		        	listOfAssets.add(holdingAsset);
		        
		        } else {
		        	System.out.println("we don fucked up"); //TODO remove line
		        }
		    }
		} catch (FileNotFoundException e) {
		    System.out.println(e.getMessage());
		} finally {
		    if (assetScan != null)
		    	assetScan.close();
		}
		
		System.out.println("Done with creating lists of objects");
		
		
		
		listOfPeople.get(0).classToXML();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
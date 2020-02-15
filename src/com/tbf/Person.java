package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import com.tbf.Address;
import com.thoughtworks.xstream.XStream;


/**
 * This is a class that models a customer for tbf
 * in the real world.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class Person {
	private String personCode;
	private String brokerState;
	private String SECIdentifier;
	private String firstName;
	private String lastName;
	private Address address;
	private ArrayList<String> emailList= new ArrayList<>();
	
	/**
	 * This is a constructor that allows for the instantiation of an 
	 * object directly from a string of data.
	 * 
	 * @param line A string of semicolon delimited data that will then be constructed
	 */
	public Person(String line) {
		String[] tokens = line.split(";",-1);
		if (tokens.length == 5) {
			String personCode = tokens[0];
			String brokerData = tokens[1];
			String fullName = tokens[2];
			this.address = new Address(tokens[3]);
			String emailAddressesString = tokens[4];
			
			String holdingBrokerState = "";
			String holdingSECIdentifier = "";
			//Separating broker data out
			String[] arrBroker = brokerData.split(",",-1);
			if(arrBroker.length == 2) {
				holdingBrokerState = arrBroker[0];
				holdingSECIdentifier = arrBroker[1];
			}
	
			this.personCode = personCode;
			this.brokerState = holdingBrokerState;
			this.SECIdentifier = holdingSECIdentifier;
			
			String[] arrNames = fullName.split(",",-1);
			this.firstName = arrNames[0];
			this.lastName = arrNames[1];
			
			String[] arrEmails = emailAddressesString.split(",",-1);
			for(int i = 0;i< arrEmails.length;i++) {
				this.emailList.add(arrEmails[i]);
			}
		}
	}
}
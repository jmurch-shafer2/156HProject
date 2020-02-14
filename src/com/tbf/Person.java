package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import com.tbf.Address;

import com.thoughtworks.xstream.XStream;

public class Person {
	private String personCode;
	private String brokerState;
	private String SECIdentifier;
	private String firstName;
	private String lastname;
	private Address address;
	private ArrayList<String> emailList= new ArrayList<>();
	
	public Person(String line) {
		String[] tokens = line.split(";",-1);
		
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
		this.lastname = arrNames[1];
		
		String[] arrEmails = emailAddressesString.split(",",-1);
		for(int i = 0;i< arrEmails.length;i++) {
			this.emailList.add(arrEmails[i]);
		}
	}
}
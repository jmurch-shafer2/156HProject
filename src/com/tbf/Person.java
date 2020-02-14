package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;

public class Person {
	private String personCode;
	private String brokerState;
	private String SECIdentifier;
	private String firstName;
	private String lastname;
	private ArrayList<String> emailList= new ArrayList<>();
	
	public Person(String line) {
		String[] tokens = line.split(";",-1);
		
		String personCode = tokens[0];
		String brokerData = tokens[1];
		String fullName = tokens[2];
		String emailAddressesString = tokens[4];
		
		//Separating broker data out
		String[] arrBroker = brokerData.split(",");
		if(arrBroker.length == 2) {
			String brokerState = arrBroker[0];
			String SECIdentifier = arrBroker[1]; 
		}
		
		
		this.personCode = personCode;
		this.brokerState = brokerState;
		this.SECIdentifier = SECIdentifier;
		
		String[] arrNames = fullName.split(",");
		this.firstName = arrNames[0];
		this.lastname = arrNames[1];
		
		String[] arrEmails = emailAddressesString.split(",");
		for(int i = 0;i< arrEmails.length;i++) {
			this.emailList.add(arrEmails[i]);
		}
	}
}
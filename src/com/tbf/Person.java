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
	
	public Person(String personCode, String brokerState, String sECIdentifier, String firstName, String lastName,
			Address address, ArrayList<String> emailList) {
		super();
		this.personCode = personCode;
		this.brokerState = brokerState;
		SECIdentifier = sECIdentifier;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emailList = emailList;
	}
}
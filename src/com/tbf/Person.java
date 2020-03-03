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
	
	public Person(String personCode, String brokerState, String SECIdentifier, String firstName, String lastName,
			Address address, ArrayList<String> emailList) {
		super();
		this.personCode = personCode;
		this.brokerState = brokerState;
		this.SECIdentifier = SECIdentifier;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emailList = emailList;
	}
	/**
	 * A basic copy constructor 
	 * @param Person to be copied 
	 * 
	 */
	public Person(Person that) {
		super();
		this.personCode = that.personCode;
		this.brokerState = that.brokerState;
		this.SECIdentifier = that.SECIdentifier;
		this.firstName = that.firstName;
		this.lastName = that.lastName;
		this.address = that.address;
		this.emailList = that.emailList;
	}

	public String getPersonCode() {
		return personCode;
	}

	public String getBrokerState() {
		return brokerState;
	}

	public String getSECIdentifier() {
		return SECIdentifier;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}

	public ArrayList<String> getEmailList() {
		return emailList;
	}
	
}
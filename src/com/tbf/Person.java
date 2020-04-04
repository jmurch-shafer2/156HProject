package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.tbf.Address;
import com.thoughtworks.xstream.XStream;

/**
 * This is a class that models a customer for tbf in the real world.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class Person {
	private int personId;
	private String personCode;
	private String brokerState;
	private String SECIdentifier;
	private String firstName;
	private String lastName;
	private Address address;
	private ArrayList<String> emailList = new ArrayList<>();

	public Person(int personId, String personCode, String brokerState, String SECIdentifier, String firstName,
			String lastName, Address address, ArrayList<String> emailList) {
		super();
		this.personId = personId;
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
	 * 
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

	public int getPersonId() {
		return personId;
	}

	public String toString() {
		String str = String.format(
				"ID: %d, Code: %s, Broker Type: %s, SEC: %s\nName: %s, %s\nAddress: %s \nEmailList: %s " + "", personId,
				personCode, brokerState, SECIdentifier, firstName, lastName, this.address.toString(),
				emailList.toString());
		return str;
	}
}
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

	/**
	 * Connects to a SQL database, and returns the email in the form of an array
	 * list of strings from a person
	 * 
	 * @param personId
	 * @return ArrayList of email Strings
	 */
	public static ArrayList<String> getEmails(int personId) {
		ArrayList<String> emailList = new ArrayList<String>();

		SQLFactory conn = new SQLFactory();

		String query = "select emailId, email from Email where personId = ?;";

		conn.startConnection();
		conn.prepareQuery(query);
		conn.setInt(personId);
		conn.runQuery();
		while (conn.next()) {
			emailList.add(conn.getString("email"));
		}
		conn.endConnection();

		return emailList;
	}

	/**
	 * Connects to a SQL database, and returns a person object from the personID
	 * 
	 * @param personId
	 * @return Person
	 */
	public static Person getPerson(int personId) {
		Person p = null;

		SQLFactory conn = new SQLFactory();

		String query = "select personId, personCode, addressId, firstName, lastName, brokerType, secIdentifier from Person p"
				+ "	where personId = ?;";

		conn.startConnection();
		conn.prepareQuery(query);
		conn.setInt(personId);

		conn.runQuery();

		String personCode = null;
		int addressId;
		String firstName = null;
		String lastName = null;
		String brokerType = null;
		String secIdentifier = null;

		if (conn.next()) {
			personCode = conn.getString("personCode");
			addressId = conn.getInt("addressId");
			firstName = conn.getString("firstName");
			lastName = conn.getString("lastName");
			brokerType = conn.getString("brokerType");
			secIdentifier = conn.getString("secIdentifier");
			Address tempAdd = Address.getAddress(addressId);
			ArrayList<String> emailList = Person.getEmails(personId);
			p = new Person(personId, personCode, brokerType, secIdentifier, firstName, lastName, tempAdd, emailList);
		} else {
			throw new IllegalStateException("no such person with personId = " + personId);
		}

		conn.endConnection();
		return p;
	}

	/**
	 * Connects to a SQL database, and returns all people in the form of an
	 * ArrayList of people objects
	 * 
	 * @return ArrayList of all people
	 */
	public static ArrayList<Person> getAllPeople() {
		ArrayList<Person> peopleList = new ArrayList<Person>();

		Person p = null;

		SQLFactory conn = new SQLFactory();

		String query = "select personId, personCode, addressId, firstName, lastName, brokerType, secIdentifier from Person p";

		conn.startConnection();
		conn.prepareQuery(query);
		conn.runQuery();

		int personId;
		String personCode = null;
		int addressId;
		String firstName = null;
		String lastName = null;
		String brokerType = null;
		String secIdentifier = null;
		Address tempAdd = null;
		ArrayList<String> emailList = null;

		while (conn.next()) {
			personId = conn.getInt("personId");
			personCode = conn.getString("personCode");
			addressId = conn.getInt("addressId");
			firstName = conn.getString("firstName");
			lastName = conn.getString("lastName");
			brokerType = conn.getString("brokerType");
			secIdentifier = conn.getString("secIdentifier");
			tempAdd = Address.getAddress(addressId);
			emailList = Person.getEmails(personId);
			p = new Person(personId, personCode, brokerType, secIdentifier, firstName, lastName, tempAdd, emailList);
			peopleList.add(p);
		}
		conn.endConnection();
		return peopleList;
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
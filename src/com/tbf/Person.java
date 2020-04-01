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

	public static ArrayList<String> getEmails(int personId) {
		ArrayList<String> emailList = new ArrayList<String>();

		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

		try {

			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// using sql ? to protect against injection attacks
		String query = "select emailId, email from Email" + "	where personId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			if (rs.next()) {
				while (rs.next()) {
					emailList.add(rs.getString("email"));
				}
			} else {
//				throw new IllegalStateException("no such email with for person with PersonId = " + personId);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return emailList;
	}

	public static Person getPerson(int personId) {
		Person p = null;

		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

		try {

			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// using sql ? to protect against injection attacks
		String query = "select personId, personCode, addressId, firstName, lastName, brokerType, secIdentifier from Person p"
				+ "	where personId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		String personCode = null;
		int addressId;
		String firstName = null;
		String lastName = null;
		String brokerType = null;
		String secIdentifier = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			if (rs.next()) {
				personCode = rs.getString("personCode");
				addressId = rs.getInt("addressId");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				brokerType = rs.getString("brokerType");
				secIdentifier = rs.getString("secIdentifier");
				Address tempAdd = Address.getAddress(addressId);
				ArrayList<String> emailList = Person.getEmails(personId);
				p = new Person(personId, personCode, brokerType, secIdentifier, firstName, lastName, tempAdd,
						emailList);
			} else {
				throw new IllegalStateException("no such person with personId = " + personId);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return p;
	}

	public static ArrayList<Person> getAllPeople() {
		ArrayList<Person> peopleList = new ArrayList<Person>();

		Person p = null;

		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

		try {

			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// using sql ? to protect against injection attacks
		String query = "select personId, personCode, addressId, firstName, lastName, brokerType, secIdentifier from Person p";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			int personId;
			String personCode = null;
			int addressId;
			String firstName = null;
			String lastName = null;
			String brokerType = null;
			String secIdentifier = null;
			Address tempAdd = null;
			ArrayList<String> emailList = null;
			
			while (rs.next()) {

				personId = rs.getInt("personId");
				personCode = rs.getString("personCode");
				addressId = rs.getInt("addressId");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				brokerType = rs.getString("brokerType");
				secIdentifier = rs.getString("secIdentifier");
				tempAdd = Address.getAddress(addressId);
				emailList = Person.getEmails(personId);
				p = new Person(personId, personCode, brokerType, secIdentifier, firstName, lastName, tempAdd,
						emailList);
				peopleList.add(p);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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

	public String toString() {
		String str = String.format(
				"ID: %d, Code: %s, Broker Type: %s, SEC: %s\nName: %s, %s\nAddress: %s \nEmailList: %s " + "", personId,
				personCode, brokerState, SECIdentifier, firstName, lastName, this.address.toString(),
				emailList.toString());
		return str;
	}
}
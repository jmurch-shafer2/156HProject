package com.tbf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class models an address in the real world.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class Address {
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private int addressId;
	
	public Address(int addressId, String street, String city, String state, String country, String zipcode) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		
	}
	
	public static Address getAddress(int addressId) {
		Address a = null;

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
		String query = "select addressId, street, city, state, zipCode, country from Address where addressId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, addressId);
			rs = ps.executeQuery();
			if(rs.next()) {
				a = new Address(addressId, rs.getString("street"),rs.getString("city"),rs.getString("state"),rs.getString("country"),rs.getString("zipCode"));
			} else {
				throw new IllegalStateException("no such Address with addressId = " + addressId);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try {
			if(rs != null && !rs.isClosed())
				rs.close();
			if(ps != null && !ps.isClosed())
				ps.close();
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return a;
	}

	/**
	 * A toString method that does additional checking to return a correct address
	 */
	public String toString() {
		String str;
		if(this.street == "") {
			return "";
		} else if(zipcode == null) {
			str = String.format("%s, %s, %s",this.street,this.city,this.country);
		} else {
			str = String.format("%s, %s, %s, %s",this.street,this.city,this.country,this.zipcode);
		}
		
		return str;
	}
	
}

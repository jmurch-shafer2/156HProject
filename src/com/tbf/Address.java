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

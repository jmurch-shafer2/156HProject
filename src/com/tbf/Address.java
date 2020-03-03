package com.tbf;

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
	
	public Address(String street, String city, String state, String country, String zipcode) {
		super();
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
		}
		if(zipcode == null) {
			str = String.format("%s\n%s, %s",this.street,this.city,this.country);
		} else {
			str = String.format("%s\n%s, %s, %s",this.street,this.city,this.country,this.zipcode);
		}
		
		return str;
	}
	
}

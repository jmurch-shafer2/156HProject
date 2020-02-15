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
	
	/**
	 * A basic constructor that allows for the instantiation of 
	 * an Address object through direct input of each field.
	 * 
	 * @param street
	 * @param city
	 * @param state
	 * @param country
	 * @param zipcode
	 */
	public Address(String street, String city, String state, String country, String zipcode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		
	}
	
	/**
	 * This constructor allows for the instantiation of an Address
	 * object directly from a string of comma delimited data.
	 * 
	 * @param line A string of comma delimited data that represents an address.
	 */
	public Address(String line) {
		String[] tokens = line.split(",",-1);
		String streetTemp = tokens[0];
		String cityTemp = tokens[1];
		String stateTemp = tokens[2];
		String zipcodeTemp = tokens[3];
		String countryTemp = tokens[4];
		this.street = streetTemp;
		this.city = cityTemp;
		this.state = stateTemp;
		this.zipcode = zipcodeTemp;
		this.country = countryTemp;
		
	}
}

package com.tbf;

/**
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
	// I don't like how ugly this is, 
	// but java made me do it because it will only let
	// us call a constructor on the first line of another 
	// constructor
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

package com.tbf;

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
		this.country = country;
		this.zipcode = zipcode;
	}
	public Address(String line) {
		String[] tokens = line.split(",",-1);
		this.street = tokens[0];
		this.city = tokens[1];
		this.state = tokens[2];
		this.country = tokens[3];
		this.zipcode = tokens[4];
	}
}

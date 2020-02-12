package cse.unl;

import java.util.ArrayList;

public class Person {
	private String personCode;
	private String brokerState;
	private String SECIdentifier;
	private String firstName;
	private String lastname;
	private ArrayList<String> emailAddresses= new ArrayList<String>();
	
	public Person(String personCode, String brokerState, String SECIdentifier, String firstName, String lastname,
			ArrayList<String> emailAddresses) {
		super(); 
		this.personCode = personCode;
		this.brokerState = brokerState;
		this.SECIdentifier = SECIdentifier;
		this.firstName = firstName;
		this.lastname = lastname;
		this.emailAddresses = emailAddresses;
	}
	
	public Person(String personCode, String brokerState, String SECIdentifier, String fullName,
			String emailAddressesString) {
		super();
		this.personCode = personCode;
		this.brokerState = brokerState;
		this.SECIdentifier = SECIdentifier;
		
		String[] arrNames = fullName.split(",");
		this.firstName = arrNames[0];
		this.lastname = arrNames[1];
		
		String[] arrEmails = emailAddressesString.split(",");
		for(int i = 0;i< arrEmails.length;i++) {
			this.emailAddresses.add(arrEmails[i]);
		}
	}
	
//	public void readLine(String line) {
//		String[] delimetedStr = line.split(";");
//		
//		Person(delimetedStr[0],delimetedStr[1],delimetedStr[2],delimetedStr[3],delimetedStr[4]);
//		
//	}
	

	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getBrokerState() {
		return brokerState;
	}
	public void setBrokerState(String brokerState) {
		this.brokerState = brokerState;
	}
	public String getSECIdentifier() {
		return SECIdentifier;
	}
	public void setSECIdentifier(String sECIdentifier) {
		SECIdentifier = sECIdentifier;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public ArrayList<String> getEmailAddresses() {
		return emailAddresses;
	}
	public void setEmailAddresses(ArrayList<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	
}

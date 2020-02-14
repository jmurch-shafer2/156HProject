package com.tbf;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import com.tbf.Person;

public class XMLConversions {
	
	
	public void classToXML(Person chris) {
		XStream xstream = new XStream();
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(chris);
		System.out.println(xml);
	}
	public void classToXML(Assets account) {
		XStream xstream = new XStream();
		xstream.alias("Private Investment", PrivateInvestment.class);
		xstream.alias("Deposit Account", DepositAccount.class);
		xstream.alias("Stock", Stock.class);
		String xml = xstream.toXML(account);
		System.out.println(xml);
	}
	public void arrPeopleToXML(ArrayList persons) {
		XStream xstream = new XStream();
		xstream.alias("ListOfPeople", ArrayList.class);
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(persons);
		System.out.println(xml);
	}
	public void arrAssetsToXML(ArrayList assets) {
		XStream xstream = new XStream();
		xstream.alias("ListOfAssets", List.class);
		xstream.alias("Private Investment", PrivateInvestment.class);
		xstream.alias("Deposit Account", DepositAccount.class);
		xstream.alias("Stock", Stock.class);
		String xml = xstream.toXML(assets);
		System.out.println(xml);
	}
}

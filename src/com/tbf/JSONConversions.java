package com.tbf;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class JSONConversions {
	public void classToJSON(Person chris) {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(chris);
		System.out.println(xml);
	}
	
	public void classToJSON(Assets account) {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("Private Investment", PrivateInvestment.class);
		xstream.alias("Deposit Account", DepositAccount.class);
		xstream.alias("Stock", Stock.class);
		String xml = xstream.toXML(account);
		System.out.println(xml);
	}
	public void arrPeopleToJSON(ArrayList persons) {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("ListOfPeople", ArrayList.class);
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(persons);
		System.out.println(xml);
	}
	public void arrAssetsToJSON(ArrayList assets) {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("ListOfAssets", List.class);
		xstream.alias("Private Investment", PrivateInvestment.class);
		xstream.alias("Deposit Account", DepositAccount.class);
		xstream.alias("Stock", Stock.class);
		String xml = xstream.toXML(assets);
		System.out.println(xml);
	}
}

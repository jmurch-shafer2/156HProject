package com.tbf;

import com.thoughtworks.xstream.XStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tbf.Person;

/**
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class XMLConversions {
	
	public void addingToFile(String writing, String savePath) throws IOException {
		BufferedWriter write = new BufferedWriter(new FileWriter(savePath, true));
		write.append(writing);
		write.close();
	}
	
	
	public void classToXML(Person chris, String path) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(chris);
		this.addingToFile(xml, path);
	}
	public void classToXML(Assets account, String path) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("Private Investment", PrivateInvestment.class);
		xstream.alias("Deposit Account", DepositAccount.class);
		xstream.alias("Stock", Stock.class);
		String xml = xstream.toXML(account);
		this.addingToFile(xml, path);
	}
	public void arrPeopleToXML(ArrayList persons, String path) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("ListOfPeople", ArrayList.class);
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(persons);
		this.addingToFile("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n", path);
		this.addingToFile(xml, path);
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

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
 * This is a class that holds all functionality for 
 * converting classes into XML.
 *
 */

public class XMLConversion {
	
	/**
	 * A utility function used only within the class in order to write to a file.
	 * 
	 * 
	 * @param writing: The string to be written to a file.
	 * @param savePath: The path from the project root directory 
	 * that the file will be saved at.
	 * @throws IOException If there is an error in writing the file,
	 * then exits with IOException
	 */
	private void addingToFile(String writing, String savePath) throws IOException {
		BufferedWriter write = new BufferedWriter(new FileWriter(savePath, true));
		write.append(writing);
		write.close();
	}
	
	/**
	 * A utility function for sending an instance of the Person class
	 * to an XML file. 
	 * 
	 * @param chris (Bourke) an instance of the person class that will be 
	 * sent to an XML file.
	 * @param savePath: The path from the project root directory 
	 * that the file will be saved at.
	 * @throws IOException If there is an error in writing the file,
	 * then exits with IOException
	 */
	public void classToXML(Person chris, String path) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(chris);
		this.addingToFile(xml, path);
	}
	/**
	 * A utility function for sending an instance of the Assets class
	 * to an XML file. 
	 * 
	 * @param account The instance of an asset class that will
	 * be sent to an XML file.
	 * @param savePath: The path from the project root directory 
	 * that the file will be saved at.
	 * @throws IOException If there is an error in writing the file,
	 * then exits with IOException
	 */
	public void classToXML(Asset account, String path) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("Private Investment", PrivateInvestment.class);
		xstream.alias("Deposit Account", DepositAccount.class);
		xstream.alias("Stock", Stock.class);
		String xml = xstream.toXML(account);
		this.addingToFile(xml, path);
	}
	
	/**
	 * A utility function used to send an array of instances of the Person
	 * class to an XML file. 
	 * 
	 * @param persons An array of instances of the Person class that 
	 * will be sent to an XML file.
	 * @param savePath: The path from the project root directory 
	 * that the file will be saved at.
	 * @throws IOException If there is an error in writing the file,
	 * then exits with IOException
	 */
	public void arrPeopleToXML(ArrayList persons, String path) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("ListOfPeople", List.class);
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(persons);
		this.addingToFile("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n", path);
		this.addingToFile(xml, path);
	}
	
	/**
	 * A utility function used to send an array of instances of the Assets
	 * class to an XML file.
	 * 
	 * @param assets An array of instances of the Asset class that 
	 * will be sent to an XML file.
	 * @param savePath: The path from the project root directory 
	 * that the file will be saved at.
	 * @throws IOException If there is an error in writing the file,
	 * then exits with IOException
	 */
	public void arrAssetsToXML(ArrayList assets, String path) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("ListOfAssets", List.class);
		xstream.alias("Private Investment", PrivateInvestment.class);
		xstream.alias("Deposit Account", DepositAccount.class);
		xstream.alias("Stock", Stock.class);
		String xml = xstream.toXML(assets);
		this.addingToFile("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n", path);
		this.addingToFile(xml, path);
	}
}

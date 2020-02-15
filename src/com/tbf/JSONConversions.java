package com.tbf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

/**
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 * 
 * This is a class that holds all functionality for 
 * converting classes into JSON.
 */

public class JSONConversions {
	
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
	public void addingToFile(String writing, String savePath) throws IOException {
		BufferedWriter write = new BufferedWriter(new FileWriter(savePath, true));
		write.append(writing);
		write.close();
	}
	
	/**
	 * A utility function for sending an instance of the Person 
	 * class to a JSON file.
	 * 
	 * @param chris (Bourke) an instance of the person class that will be 
	 * sent to an JSON file.
	 * @param path The path from the project root directory 
	 * that the file will be saved at.
	 * @throws IOException If there is an error in writing the file,
	 * then exits with IOException
	 */
	public void classToJSON(Person chris, String path) throws IOException {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(chris);
		this.addingToFile(xml, path);
	}
	
	/**
	 * A utility function for sending an instance of the Assets
	 * class to a JSON file.
	 * 
	 * @param account The instance of an asset class that will
	 * be sent to an JSON file.
	 * @param path The path from the project root directory 
	 * that the file will be saved at
	 * @throws IOException If there is an error in writing the file,
	 * then exits with IOException
	 */
	public void classToJSON(Assets account, String path) throws IOException {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("Private Investment", PrivateInvestment.class);
		xstream.alias("Deposit Account", DepositAccount.class);
		xstream.alias("Stock", Stock.class);
		String xml = xstream.toXML(account);
		this.addingToFile(xml, path);
	}
	
	/**
	 * A utility function used to send an array of instances of the Person
	 * class to an JSON file. 
	 * 
	 * @param persons An array of instances of the Person class that 
	 * will be sent to an JSON file.
	 * @param path The path from the project root directory 
	 * that the file will be saved at.
	 * @throws IOException If there is an error in writing the file,
	 * then exits with IOException
	 */
	public void arrPeopleToJSON(ArrayList persons, String path) throws IOException {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("ListOfPeople", ArrayList.class);
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(persons);
		this.addingToFile(xml, path);
	}
	/**
	 * A utility function used to send an array of instances of the Assets
	 * class to an JSON file.
	 * @param assets An array of instances of the Asset class that 
	 * will be sent to an JSON file.
	 * @param path The path from the project root directory 
	 * that the file will be saved at.
	 * @throws IOException If there is an error in writing the file,
	 * then exits with IOException
	 */
	public void arrAssetsToJSON(ArrayList assets, String path) throws IOException {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.alias("ListOfAssets", List.class);
		xstream.alias("Private Investment", PrivateInvestment.class);
		xstream.alias("Deposit Account", DepositAccount.class);
		xstream.alias("Stock", Stock.class);
		String xml = xstream.toXML(assets);
		this.addingToFile(xml, path);
	}
}

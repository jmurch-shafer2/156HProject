package com.tbf;

import com.thoughtworks.xstream.XStream;

import com.tbf.Person;

public class XMLConversions {
	
	
	public void classToXML(Person chris) {
		XStream xstream = new XStream();
		xstream.alias("Person", Person.class);
		xstream.alias("Email", String.class);
		String xml = xstream.toXML(chris);
		System.out.println(xml);
	}
	
}

package com.tbf;

public class TestingDriver {
	public static void main(String[] argv) {
		System.out.println("Address");
		Address add = Address.getAddress(1);
		System.out.println(add.toString() + "\n");
	
		System.out.println("Person");
		Person perp = Person.getPerson(3);
		System.out.println(perp.toString() + "\n");
	}
}

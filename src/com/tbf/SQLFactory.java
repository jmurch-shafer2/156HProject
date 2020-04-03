package com.tbf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLFactory {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public void startConnection() {
		
		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

		try {

			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void prepareQuery(String query) {
		try {
			this.ps = conn.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void runQuery() {
		rs = null;
		try {
			rs = this.ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean next() {
		try{
			if(this.rs != null && this.rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getInt(String name) {
		int tempInt = 0;
		try {
			tempInt = this.rs.getInt(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempInt;
	}
	public String getString(String name) {
		String tempString = null;
		try {
			tempString = this.rs.getString(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempString;
	}
//	
//	while (conn.next()) {
//
//		personId = rs.getInt("personId");
//		personCode = rs.getString("personCode");
//		addressId = rs.getInt("addressId");
//		firstName = rs.getString("firstName");
//		lastName = rs.getString("lastName");
//		brokerType = rs.getString("brokerType");
//		secIdentifier = rs.getString("secIdentifier");
//		tempAdd = Address.getAddress(addressId);
//		emailList = Person.getEmails(personId);
//		p = new Person(personId, personCode, brokerType, secIdentifier, firstName, lastName, tempAdd,
//				emailList);
//		peopleList.add(p);
//	}
	
	
	
	
	
	
	
	
	
	
	
	public void endConnection() {
		try {
			if (this.rs != null && !this.rs.isClosed())
				this.rs.close();
			if (this.ps != null && !this.ps.isClosed())
				this.ps.close();
			if (this.conn != null && !this.conn.isClosed())
				this.conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}

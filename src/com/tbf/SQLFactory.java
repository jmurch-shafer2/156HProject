package com.tbf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class SQLFactory {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	int counter = 1;
	
	/**
	 * begins connection with database
	 */
	public void startConnection() {
		Logger log = Logger.getLogger(PortfolioReport.class);
		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

		try {

			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			log.error(e, new RuntimeException(e));
		}

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			log.error(e, new RuntimeException(e));
		}
	}

	/**
	 * prepares query
	 */
	public void prepareQuery(String query) {
		Logger log = Logger.getLogger(PortfolioReport.class);
		try {
			this.ps = conn.prepareStatement(query);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
	}

	public void runQuery() {
		Logger log = Logger.getLogger(PortfolioReport.class);
		rs = null;
		try {
			rs = this.ps.executeQuery();
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
	}

	public boolean next() {
		Logger log = Logger.getLogger(PortfolioReport.class);
		try {
			if (this.rs != null && this.rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		return false;
	}

	public void setInt(int temp) {
		Logger log = Logger.getLogger(PortfolioReport.class);
		try {
			this.ps.setInt(this.counter, temp);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		counter++;
	}

	public void setString(String temp) {
		Logger log = Logger.getLogger(PortfolioReport.class);
		try {
			this.ps.setString(this.counter, temp);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		counter++;
	}

	public int getInt(String name) {
		Logger log = Logger.getLogger(PortfolioReport.class);
		int tempInt = 0;
		try {
			tempInt = this.rs.getInt(name);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		return tempInt;
	}

	public double getDouble(String name) {
		Logger log = Logger.getLogger(PortfolioReport.class);
		double tempDouble = 0;
		try {
			tempDouble = this.rs.getDouble(name);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		return tempDouble;
	}

	public String getString(String name) {
		Logger log = Logger.getLogger(PortfolioReport.class);
		String tempString = null;
		try {
			tempString = this.rs.getString(name);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		return tempString;
	}

	public void endConnection() {
		Logger log = Logger.getLogger(PortfolioReport.class);
		try {
			if (this.rs != null && !this.rs.isClosed())
				this.rs.close();
			if (this.ps != null && !this.ps.isClosed())
				this.ps.close();
			if (this.conn != null && !this.conn.isClosed())
				this.conn.close();
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
	}
}

package com.tbf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * Factory class to help automate connecting to a MariaDB 
 *
 */
public class SQLFactory {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	int counter = 1;
	Logger log = Logger.getLogger(PortfolioReport.class);;

	/**
	 * Begins connection with database and prepares query
	 */
	public void startConnection(String query) {
//		Logger log = Logger.getLogger(PortfolioReport.class);;
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
		try {
			this.ps = conn.prepareStatement(query);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
	}

	/**
	 * Runs the query and returns a result set stored in the instance of the class
	 */
	public void runQuery() {
		rs = null;
		try {
			rs = this.ps.executeQuery();
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
	}
	
	/**
	 * Runs the query in the case of an update or deletion
	 */
	public void runUpdate() {
//		Logger log = Logger.getLogger(PortfolioReport.class);;
		try {
			this.ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
	}

	/**
	 * Returns true if there is a next row in the result set
	 * @return
	 */
	public boolean next() {
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

	/**
	 * sets the next int in a prepared statement stored in the
	 * instance of the class
	 * @param temp
	 */
	public void setInt(int temp) {
		try {
			this.ps.setInt(this.counter, temp);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		counter++;
	}

	/**
	 * sets the next String in a prepared statement 
	 * stored in the instance of the class 
	 * @param temp
	 */
	public void setString(String temp) {
		try {
			this.ps.setString(this.counter, temp);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		counter++;
	}
	
	/**
	 * sets the next Double in a prepared statement 
	 * stored in the instance of the class 
	 * @param temp
	 */
	public void setDouble(Double temp) {
		try {
			this.ps.setDouble(this.counter, temp);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		counter++;
	}

	/**
	 * Returns the integer in the column with the column
	 * title of the parameter
	 * @param name
	 * @return
	 */
	public int getInt(String name) {
		int tempInt = 0;
		try {
			tempInt = this.rs.getInt(name);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		return tempInt;
	}

	/**
	 * Returns the double in the column with the column
	 * title of the parameter
	 * @param name
	 * @return
	 */
	public double getDouble(String name) {
		double tempDouble = 0;
		try {
			tempDouble = this.rs.getDouble(name);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		return tempDouble;
	}

	/**
	 * Returns the String in the column with the column
	 * title of the parameter
	 * @param name
	 * @return
	 */
	public String getString(String name) {
		String tempString = null;
		try {
			tempString = this.rs.getString(name);
		} catch (SQLException e) {
			log.error(e, new RuntimeException(e));
		}
		return tempString;
	}

	/**
	 * ends the connection to the database
	 */
	public void endConnection() {
//		Logger log = Logger.getLogger(PortfolioReport.class);;
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

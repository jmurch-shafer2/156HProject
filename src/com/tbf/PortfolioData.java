package com.tbf;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class PortfolioData {
	//DONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		String email = "delete from Email;";
		String person = "delete from Person;";
		String address = "delete from Address;";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(email);
		conn.runUpdate();
		conn.startConnection(person);
		conn.runUpdate();
		conn.startConnection(address);
		conn.runUpdate();

		conn.endConnection();
	}

	/**
	 * Removes the person record from the database corresponding to the provided
	 * <code>personCode</code>
	 * 
	 * @param personCode
	 */
	public static void removePerson(String personCode) {
		
		
	}

	/**
	 * Method to add a person record to the database with the provided data. The
	 * <code>brokerType</code> will either be "E" or "J" (Expert or Junior) or
	 * <code>null</code> if the person is not a broker.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 * @param brokerType
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city,
			String state, String zip, String country, String brokerType, String secBrokerId) {
		//TODO check for duplicate address
		
		//dont have to check for duplicates because the design of the database will not allow duplicate personCodes
		
		String address = "INSERT Address(street, city, state, zipCode, country) VALUES (?, ?, ?, ?, ?);";
		String person = "INSERT Person(personCode, firstName, lastName, brokerType, secIdentifier) VALUES (?, ?, ?, ?, ?);";
		
		SQLFactory conn = new SQLFactory();
		
		conn.startConnection(address);
		conn.setString(street);
		conn.setString(city);
		conn.setString(state);
		conn.setInt(Integer.parseInt(zip));
		conn.setString(country);
		conn.runUpdate();
		//run select to find address id
		//String newQuery = "SELECT addressId where
		
		conn.startConnection(person);
		conn.setString(personCode);
		conn.setString(firstName);
		conn.setString(lastName);
		conn.setString(brokerType);
		conn.setString(secBrokerId);
		conn.runUpdate();
		
		conn.endConnection();
	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		//personcode or personId
		//TODO check for email duplicates
		
		String query = "INSERT Email(personCode, email) VALUES (?, ?);";
		
		SQLFactory conn = new SQLFactory();
		conn.startConnection(query);
		conn.setString(personCode);
		conn.setString(email);
		conn.runUpdate();
		conn.endConnection();
	}

	/**
	 * Removes all asset records from the database
	 */
	public static void removeAllAssets() {
		
	}

	/**
	 * Removes the asset record from the database corresponding to the provided
	 * <code>assetCode</code>
	 * 
	 * @param assetCode
	 */
	public static void removeAsset(String assetCode) {
	}

	/**
	 * Adds a deposit account asset record to the database with the provided data.
	 * 
	 * @param assetCode
	 * @param label
	 * @param apr
	 */
	public static void addDepositAccount(String assetCode, String label, double apr) {
		//TODO finish/fix query comparing assetCode of account to be added to all other accounts in the database
		String checkAssetCode = "SELECT assetCode from ???;";
		if(checkAssetCode.equals(assetCode)) {
			exit();
		}
			
		String query = "INSERT Asset(assetCode, label, apr) VALUES (?, ?, ?);";
		
		SQLFactory conn = new SQLFactory();
		conn.startConnection(query);
		conn.setString(assetCode);
		conn.setString(label);
		conn.setDouble(apr);
		conn.runUpdate();
		conn.endConnection();
	}

	/**
	 * Adds a private investment asset record to the database with the provided
	 * data.
	 * 
	 * @param assetCode
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn
	 * @param baseOmega
	 * @param totalValue
	 */
	public static void addPrivateInvestment(String assetCode, String label, Double quarterlyDividend,
			Double baseRateOfReturn, Double baseOmega, Double totalValue) {
		String query = "INSERT Asset(assetCode, label, quarterlyDividend, baseRateOfReturn, baseOmega, totalValue) VALUES (?, ?, ?, ?, ?, ?);"; 
		SQLFactory conn = new SQLFactory();
		conn.startConnection(query);
		conn.setString(assetCode);
		conn.setString(label);
		conn.setDouble(quarterlyDividend);
		conn.setDouble(baseRateOfReturn);
		conn.setDouble(baseOmega);
		conn.setDouble(totalValue);
		conn.runUpdate();
		conn.endConnection();
	}

	/**
	 * Adds a stock asset record to the database with the provided data.
	 * 
	 * @param assetCode
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn
	 * @param beta
	 * @param stockSymbol
	 * @param sharePrice
	 */
	
	public static void addStock(String assetCode, String label, Double quarterlyDividend, Double baseRateOfReturn,
			Double beta, String stockSymbol, Double sharePrice) {
		String query = "INSERT stock(assetCode, label, quarterlyDividend, baseRateOfReturn, beta, stockSymbol, sharePrice) VALUES (?, ?, ?, ?, ?, ?, ?);";
		SQLFactory conn = new SQLFactory();
		conn.startConnection(query);
		conn.setString(assetCode);
		conn.setString(label);
		conn.setDouble(quarterlyDividend);
		conn.setDouble(baseRateOfReturn);
		conn.setDouble(beta);
		conn.setString(stockSymbol);
		conn.setDouble(sharePrice);
		conn.runUpdate();
		conn.endConnection();
	}

	/**
	 * Removes all portfolio records from the database
	 */
	public static void removeAllPortfolios() {
		String portfolioAsset = "delete from PortfolioAsset;";
		String portfolio = "delete from Portfolio;";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(portfolioAsset);
		conn.runUpdate();
		conn.startConnection(portfolio);
		conn.runUpdate();
		conn.endConnection();
	}

	/**
	 * Removes the portfolio record from the database corresponding to the provided
	 * <code>portfolioCode</code>
	 * 
	 * @param portfolioCode
	 */
	public static void removePortfolio(String portfolioCode) {
	}

	/**
	 * Adds a portfolio records to the database with the given data. If the
	 * portfolio has no beneficiary, the <code>beneficiaryCode</code> will be
	 * <code>null</code>
	 * 
	 * @param portfolioCode
	 * @param ownerCode
	 * @param managerCode
	 * @param beneficiaryCode
	 */
	public static void addPortfolio(String portfolioCode, String ownerCode, String managerCode,
			String beneficiaryCode) {
		String query = "INSERT Portfolio(portfolioCode, ownerId, managerId, beneficiaryID) VALUES (?, ?, ?, ?);";
		
		SQLFactory conn = new SQLFactory();
		conn.startConnection(query);
		conn.setString(portfolioCode);
		conn.setString(ownerCode);
		conn.setString(managerCode);
		conn.setString(beneficiaryCode);
		conn.runUpdate();
		conn.endConnection();
	}

	/**
	 * Associates the asset record corresponding to <code>assetCode</code> with the
	 * portfolio corresponding to the provided <code>portfolioCode</code>. The third
	 * parameter, <code>value</code> is interpreted as a <i>balance</i>, <i>number
	 * of shares</i> or <i>stake percentage</i> depending on the type of asset the
	 * <code>assetCode</code> is associated with.
	 * 
	 * @param portfolioCode
	 * @param assetCode
	 * @param value
	 */
	public static void addAsset(String portfolioCode, String assetCode, double value) {
		String query = "INSERT Asset(portfolioCode, assetCode, value) VALUES (?, ?, ?);";
		
		SQLFactory conn = new SQLFactory();
		conn.startConnection(query);
		conn.setString(portfolioCode);
		conn.setString(assetCode);
		conn.setDouble(value);
		conn.runUpdate();
		conn.endConnection();
	}

}

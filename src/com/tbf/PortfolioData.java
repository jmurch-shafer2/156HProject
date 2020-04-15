package com.tbf;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class PortfolioData {
	// DONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		String emailDel = "delete from Email;";
		String personDel = "delete from Person;";
		String addressDel = "delete from Address;";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(emailDel);
		conn.runUpdate();
		conn.startConnection(personDel);
		conn.runUpdate();
		conn.startConnection(addressDel);
		conn.runUpdate();

		conn.endConnection();
	}

//	TODO add in check for duplicate addresses

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
		// check for duplicates, dont add a repeat person or address
		// check the titles of table names, differentiate between the column title and
		// the passed in variable
		// how to connect person with address
		String address = "INSERT Address (street, city, state, zip, country) VALUES (street, city, state, zip, country)";
		String person = "INSERT Person(personCode, firstName, lastName, brokerType, secIdentifier) VALUES (personCode, firstName, lastName, address, brokerType, secBrokerId)";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(address);
		
		conn.runUpdate();
		conn.startConnection(person);
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
		// personcode or personId
		String query = "INSERT Email(personCode, email) VALUES (?,?)";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(query);
		conn.setInt(Integer.parseInt(personCode));
		conn.setString(email);
		conn.runUpdate();
		conn.endConnection();
	}

	/**
	 * Removes all asset records from the database
	 */
	public static void removeAllAssets() {
		"delete from Asset;"
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
		"insert Asset (assetCode,typeOfAsset,apr,totalValue,label) values ('00e','D',0.57,1599,'Savings account');"

		String depositAccount = "INSERT INTO Asset (assetCode, label, apr) VALUES (assetCode, label, apr)";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(depositAccount);
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
		String privateInvestment = "INSERT INTO Asset (assetCode, label, quarterlyDividend, baseRateOfReturn, baseOmega, totalValue) VALUES (assetCode, label, quarterlyDividend, baseRateOfReturn, baseOmega, totalValue)";
		SQLFactory conn = new SQLFactory();
		conn.startConnection(privateInvestment);
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
		String stock = "INSERT INTO Asset (assetCode, label, quarterlyDividend, baseRateOfReturn, beta, stockSymbol, sharePrice) VALUES (assetCode, label, quarterlyDividend, baseRateOfReturn, beta, stockSymbol, sharePrice)";
		SQLFactory conn = new SQLFactory();
		conn.startConnection(stock);
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
		String portfolio = "INSERT INTO Portfolio (portfolioCode, ownerId, managerId, beneficiaryID) VALUES (portfolioCode, ownerCode, managerCode, beneficiaryCode)";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(portfolio);
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
	}

}

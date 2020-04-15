package com.tbf;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class PortfolioData {
	// DONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE, TODO need to test
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

	// DONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE, TODO need to test
	/**
	 * Removes the person record from the database corresponding to the provided
	 * <code>personCode</code>
	 * 
	 * @param personCode
	 */
	public static void removePerson(String personCode) {
		int personId = DataLoader.getPersonId(personCode);
		
		SQLFactory emailDel = new SQLFactory();
		String emailQuery = "delete from Email where personId = ?";
		emailDel.startConnection(emailQuery);
		emailDel.setInt(personId);
		emailDel.runUpdate();
		emailDel.endConnection();
		
		SQLFactory personDel = new SQLFactory();
		String personQuery = "delete from Person where personId = ?";
		personDel.startConnection(personQuery);
		personDel.setInt(personId);
		personDel.runUpdate();
		personDel.endConnection();
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
		// TODO check for duplicate address

		// dont have to check for duplicates because the design of the database will not
		// allow duplicate personCodes

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
		// run select to find address id
		// String newQuery = "SELECT addressId where

		conn.startConnection(person);
		conn.setString(personCode);
		conn.setString(firstName);
		conn.setString(lastName);
		conn.setString(brokerType);
		conn.setString(secBrokerId);
		conn.runUpdate();

		conn.endConnection();
	}
	
	// WORKS Great for now
	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		// TODO check for email duplicates
		int personId = DataLoader.getPersonId(personCode);

		SQLFactory conn = new SQLFactory();
		String query = "INSERT Email(personId, email) VALUES (?, ?);";
		conn.startConnection(query);
		conn.setInt(personId);
		conn.setString(email);
		conn.runUpdate();
		conn.endConnection();
	}

	
	
	
	
	
	// Should work TODO need to test, has not been tested
	/**
	 * Removes all asset records from the database
	 */
	public static void removeAllAssets() {
		String asset = "delete from Asset;";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(asset);
		conn.runUpdate();

		conn.endConnection();
	}

	
	
	
	
	
	
	
	
	// WORKS, need TODO test more throughly
	/**
	 * Removes the asset record from the database corresponding to the provided
	 * <code>assetCode</code>
	 * 
	 * @param assetCode
	 */
	public static void removeAsset(String assetCode) {
		SQLFactory request = new SQLFactory();
		String requestQuery = "select assetId from Asset where assetCode = ?";
		request.startConnection(requestQuery);
		request.setString(assetCode);
		request.runQuery();
		int assetId = 0;
		if(request.next()) {
			assetId = request.getInt("assetId");
		}
		
		SQLFactory portfolioAssetDel = new SQLFactory();
		String portfolioAssetQuery = "delete from PortfolioAsset where assetId = ?";
		portfolioAssetDel.startConnection(portfolioAssetQuery);
		portfolioAssetDel.setInt(assetId);
		portfolioAssetDel.runUpdate();
		portfolioAssetDel.endConnection();
		
		
		SQLFactory assetDel = new SQLFactory();
		String assetQuery = "delete from Asset where assetId = ?";
		assetDel.startConnection(assetQuery);
		assetDel.setInt(assetId);
		assetDel.runUpdate();
		assetDel.endConnection();
		
		
	}

	

	
	
	
	 
	
	// TODO finish/fix query comparing assetCode of account to be added to all other, I THINK DONE??? PLEASE CHECK TODO TODO
	/**
	 * Adds a deposit account asset record to the database with the provided data.
	 * 
	 * @param assetCode
	 * @param label
	 * @param apr
	 * @throws Exception 
	 */
	public static void addDepositAccount(String assetCode, String label, double apr) throws Exception {
		String checkAssetCode = "SELECT assetCode from Asset;";
		SQLFactory check = new SQLFactory();
		check.startConnection(checkAssetCode);
		check.runQuery();
		while(check.next()) {
			if(check.getString("assetCode").equals(assetCode)) {
				throw new Exception("Duplicate deposit Account in database");
			}
		}
		check.endConnection();
		
		String depositAccountQuery = "insert Asset (typeOfAsset,assetCode,label,apr) values ('D',?,?,?);";

		SQLFactory depositAccountConn = new SQLFactory();
		depositAccountConn.startConnection(depositAccountQuery);
		depositAccountConn.setString(assetCode);
		depositAccountConn.setString(label);
		depositAccountConn.setDouble(apr);
		depositAccountConn.runUpdate();
		depositAccountConn.endConnection();
	}

	// TODO finish/fix query comparing assetCode of account to be added to all other, I THINK DONE??? PLEASE CHECK TODO TODO
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
	 * @throws Exception 
	 */
	public static void addPrivateInvestment(String assetCode, String label, Double quarterlyDividend,
		Double baseRateOfReturn, Double baseOmega, Double totalValue) throws Exception {
		String checkAssetCode = "SELECT assetCode from Asset;";
		SQLFactory check = new SQLFactory();
		check.startConnection(checkAssetCode);
		check.runQuery();
		while(check.next()) {
			if(check.getString("assetCode").equals(assetCode)) {
				throw new Exception("Duplicate Private Investment in database");
			}
		}
		check.endConnection();
		
		String privateInvestmentQuery = "insert Asset (typeOfAsset,assetCode,name,quarterlyDividend,baseRateReturn,baseOmegaMeasure,totalValue) values ('P',?,?,?,?,?,?,?);";
		SQLFactory PIConn = new SQLFactory();
		PIConn.startConnection(privateInvestmentQuery);
		PIConn.setString(assetCode);
		PIConn.setString(label);
		PIConn.setDouble(quarterlyDividend);
		PIConn.setDouble(baseRateOfReturn);
		PIConn.setDouble(baseOmega);
		PIConn.setDouble(totalValue);
		PIConn.runUpdate();
		PIConn.endConnection();
	}
	
	// TODO finish/fix query comparing assetCode of account to be added to all other, I THINK DONE??? PLEASE CHECK TODO TODO
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
	 * @throws Exception 
	 */
	public static void addStock(String assetCode, String label, Double quarterlyDividend, Double baseRateOfReturn,
			Double beta, String stockSymbol, Double sharePrice) throws Exception {
		String checkAssetCode = "SELECT assetCode from Asset;";
		SQLFactory check = new SQLFactory();
		check.startConnection(checkAssetCode);
		check.runQuery();
		while(check.next()) {
			if(check.getString("assetCode").equals(assetCode)) {
				throw new Exception("Duplicate Stock in database");
			}
		}
		check.endConnection();
		
		String query = "insert Asset (typeOfAsset,assetCode,label,quarterlyDividend,baseRateReturn,betaMeasure,stockSymbol,sharePrice) values ('S',?,?,?,?,?,?,?);";
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

	
	
	
	
	
	
	
	
	
	
	// Works well
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

	
	
	
	
	
	
	
	
	
	
	// TODO should work but don't know	
	/**
	 * Removes the portfolio record from the database corresponding to the provided
	 * <code>portfolioCode</code>
	 * 
	 * @param portfolioCode
	 */
	public static void removePortfolio(String portfolioCode) {
		SQLFactory request = new SQLFactory();
		String requestQuery = "select portfolioId from Portfolio where portfolioCode = ?";
		request.startConnection(requestQuery);
		request.setString(portfolioCode);
		request.runQuery();
		int portfolioId = 0;
		if(request.next()) {
			portfolioId = request.getInt("portfolioId");
		}
		
		SQLFactory portfolioAssetDel = new SQLFactory();
		String portfolioAssetQuery = "delete from PortfolioAsset where portfolioId = ?";
		portfolioAssetDel.startConnection(portfolioAssetQuery);
		portfolioAssetDel.setInt(portfolioId);
		portfolioAssetDel.runUpdate();
		portfolioAssetDel.endConnection();
		
		SQLFactory portfolioDel = new SQLFactory();
		String assetQuery = "delete from Portfolio where portfolioId = ?";
		portfolioDel.startConnection(assetQuery);
		portfolioDel.setInt(portfolioId);
		portfolioDel.runUpdate();
		portfolioDel.endConnection();
	}

	
	
	
	
	// TODO should work but don't know
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
		int ownerId = DataLoader.getPersonId(ownerCode);
		int managerId = DataLoader.getPersonId(managerCode);
		int beneficiaryId = DataLoader.getPersonId(beneficiaryCode);
		String query = "insert Portfolio(portfolioCode,ownerId,managerId,beneficiaryId) values (?,?,?,?);";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(query);
		conn.setString(portfolioCode);
		conn.setInt(ownerId);
		conn.setInt(managerId);
		conn.setInt(beneficiaryId);
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
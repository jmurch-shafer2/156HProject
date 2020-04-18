package com.tbf;

import org.apache.log4j.Logger;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class PortfolioData {
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
		Logger log = Logger.getLogger(PortfolioReport.class);
		String duplicateQuery = "select personCode from Person";
		SQLFactory duplicateConn = new SQLFactory();
		duplicateConn.startConnection(duplicateQuery);
		duplicateConn.runQuery();
		while (duplicateConn.next()) {
			if (duplicateConn.getString("personCode").equals(personCode)) {
				log.error("Attempting to add a duplicate person");
			}
		}
		duplicateConn.endConnection();

		String addressQuery = "insert Address (street, city, state, zipCode, country) values (?,?,?,?,?);";
		SQLFactory addressConn = new SQLFactory();
		addressConn.startConnection(addressQuery);
		addressConn.setString(street);
		addressConn.setString(city);
		addressConn.setString(state);
		addressConn.setString(zip);
		addressConn.setString(country);
		addressConn.runUpdate();
		addressConn.endConnection();

		String request = "select addressId from Address" + " where street = ? && city = ? && state = ?;";
		SQLFactory requestConn = new SQLFactory();
		requestConn.startConnection(request);
		requestConn.setString(street);
		requestConn.setString(city);
		requestConn.setString(state);
		requestConn.runQuery();
		int addressId = 0;
		if (requestConn.next()) {
			addressId = requestConn.getInt("addressId");
		}
		requestConn.endConnection();

		String person = "insert Person(personCode, addressId, firstName, lastName, brokerType, secIdentifier) values (?,?,?,?,?,?);";
		SQLFactory personConn = new SQLFactory();
		personConn.startConnection(person);
		personConn.setString(personCode);
		personConn.setInt(addressId);
		personConn.setString(firstName);
		personConn.setString(lastName);
		personConn.setString(brokerType);
		personConn.setString(secBrokerId);
		personConn.runUpdate();
		personConn.endConnection();
	}

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
		if (request.next()) {
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

	/**
	 * Adds a deposit account asset record to the database with the provided data.
	 * 
	 * @param assetCode
	 * @param label
	 * @param apr
	 */
	public static void addDepositAccount(String assetCode, String label, double apr) {
		Logger log = Logger.getLogger(PortfolioReport.class);
		String checkAssetCode = "SELECT assetCode from Asset;";
		SQLFactory check = new SQLFactory();
		check.startConnection(checkAssetCode);
		check.runQuery();
		while (check.next()) {
			if (check.getString("assetCode").equals(assetCode)) {
				log.error("Duplicate deposit Account in database");
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
		Logger log = Logger.getLogger(PortfolioReport.class);
		String checkAssetCode = "SELECT assetCode from Asset;";
		SQLFactory check = new SQLFactory();
		check.startConnection(checkAssetCode);
		check.runQuery();
		while (check.next()) {
			if (check.getString("assetCode").equals(assetCode)) {
				log.error("Duplicate Private Investment in database");
			}
		}
		check.endConnection();

		String privateInvestmentQuery = "insert Asset (typeOfAsset,assetCode,label,quarterlyDividend,baseRateReturn,baseOmegaMeasure,totalValue) values ('P',?,?,?,?,?,?);";
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
		Logger log = Logger.getLogger(PortfolioReport.class);
		String checkAssetCode = "SELECT assetCode from Asset;";
		SQLFactory check = new SQLFactory();
		check.startConnection(checkAssetCode);
		check.runQuery();
		while (check.next()) {
			if (check.getString("assetCode").equals(assetCode)) {
				log.error("Duplicate Stock in database");
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
		SQLFactory request = new SQLFactory();
		String requestQuery = "select portfolioId from Portfolio where portfolioCode = ?";
		request.startConnection(requestQuery);
		request.setString(portfolioCode);
		request.runQuery();
		int portfolioId = 0;
		if (request.next()) {
			portfolioId = request.getInt("portfolioId");
		}
		request.endConnection();

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
		int beneficiaryId;

		String query = "insert Portfolio(portfolioCode,ownerId,managerId,beneficiaryId) values (?,?,?,?);";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(query);
		conn.setString(portfolioCode);
		conn.setInt(ownerId);
		conn.setInt(managerId);
		if (beneficiaryCode == null || beneficiaryCode.equals("")) {
			conn.setNullInt();
		} else {
			beneficiaryId = DataLoader.getPersonId(beneficiaryCode);
			conn.setInt(beneficiaryId);
		}
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
		Logger log = Logger.getLogger(PortfolioReport.class);
		int assetId = 0;
		int portfolioId = 0;
		String typeOfAsset = null;

		String assetQuery = "select assetId,typeOfAsset from Asset where assetCode = ?";
		SQLFactory assetRequest = new SQLFactory();
		assetRequest.startConnection(assetQuery);
		assetRequest.setString(assetCode);
		assetRequest.runQuery();
		if (assetRequest.next()) {
			assetId = assetRequest.getInt("assetId");
			typeOfAsset = assetRequest.getString("typeOfAsset");
		} else {
			log.error("Asset does not exist");
		}
		assetRequest.endConnection();

		SQLFactory updateValue = new SQLFactory();
		if (typeOfAsset.equals("P")) {
			String privateQuery = "update Asset set percentageOwned = ? where assetId = ?;";
			updateValue.startConnection(privateQuery);
		} else if (typeOfAsset.equals("D")) {
			String privateQuery = "update Asset set totalValue = ? where assetId = ?;";
			updateValue.startConnection(privateQuery);
		} else if (typeOfAsset.equals("S")) {
			String privateQuery = "update Asset set sharesOwned = ? where assetId = ?;";
			updateValue.startConnection(privateQuery);
		} else {
			log.error("Asset type is invalid");
		}
		updateValue.setDouble(value);
		updateValue.setInt(assetId);
		updateValue.runUpdate();
		updateValue.endConnection();

		String portfolioQuery = "select portfolioId from Portfolio where portfolioCode = ?";
		SQLFactory portfolioRequest = new SQLFactory();
		portfolioRequest.startConnection(portfolioQuery);
		portfolioRequest.setString(portfolioCode);
		portfolioRequest.runQuery();
		if (portfolioRequest.next()) {
			portfolioId = portfolioRequest.getInt("portfolioId");
		} else {
			log.error("Portfolio does not exist");
		}
		portfolioRequest.endConnection();

		String query = "insert PortfolioAsset(assetId,portfolioId) values (?,?);";

		SQLFactory conn = new SQLFactory();
		conn.startConnection(query);
		conn.setInt(assetId);
		conn.setInt(portfolioId);
		conn.runUpdate();
		conn.endConnection();
	}

}
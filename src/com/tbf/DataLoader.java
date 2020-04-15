package com.tbf;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataLoader {

	public static Address getAddress(int addressId) {
		Address a = null;

		SQLFactory conn = new SQLFactory();

		// using sql ? to protect against injection attacks
		String query = "select addressId, street, city, state, zipCode, country from Address where addressId = ?;";

		conn.startConnection(query);
		conn.setInt(addressId);
		conn.runQuery();

		if (conn.next()) {
			a = new Address(addressId, conn.getString("street"), conn.getString("city"), conn.getString("state"),
					conn.getString("country"), conn.getString("zipCode"));
		}

		conn.endConnection();
		return a;
	}

	/**
	 * Connects to a SQL database, and returns the email in the form of an array
	 * list of strings from a person
	 * 
	 * @param personId
	 * @return ArrayList of email Strings
	 */
	public static ArrayList<String> getEmails(int personId) {
		ArrayList<String> emailList = new ArrayList<String>();

		SQLFactory conn = new SQLFactory();

		String query = "select emailId, email from Email where personId = ?;";

		conn.startConnection(query);
		conn.setInt(personId);
		conn.runQuery();
		while (conn.next()) {
			emailList.add(conn.getString("email"));
		}
		conn.endConnection();

		return emailList;
	}

	/**
	 * Connects to a SQL database, and returns all people in the form of an
	 * ArrayList of people objects
	 * 
	 * @return ArrayList of all people
	 */
	public static ArrayList<Person> getAllPeople() {
		ArrayList<Person> peopleList = new ArrayList<Person>();

		Person p = null;

		SQLFactory conn = new SQLFactory();

		String query = "select personId, personCode, addressId, firstName, lastName, brokerType, secIdentifier from Person p";

		conn.startConnection(query);
		conn.runQuery();

		int personId;
		String personCode = null;
		int addressId;
		String firstName = null;
		String lastName = null;
		String brokerType = null;
		String secIdentifier = null;
		Address tempAdd = null;
		ArrayList<String> emailList = null;

		while (conn.next()) {
			personId = conn.getInt("personId");
			personCode = conn.getString("personCode");
			addressId = conn.getInt("addressId");
			firstName = conn.getString("firstName");
			lastName = conn.getString("lastName");
			brokerType = conn.getString("brokerType");
			secIdentifier = conn.getString("secIdentifier");
			tempAdd = DataLoader.getAddress(addressId);
			emailList = DataLoader.getEmails(personId);
			p = new Person(personId, personCode, brokerType, secIdentifier, firstName, lastName, tempAdd, emailList);
			peopleList.add(p);
		}
		conn.endConnection();
		return peopleList;
	}
	
	
	/**
	 * Connects to a sql database and then returns the personId from a personCode
	 * @param personId
	 * @return
	 */
	public static int getPersonId(String personCode) {
		SQLFactory request = new SQLFactory();
	
		String personIdQuery = "SELECT personId from Person where personCode = ?;";
		request.startConnection(personIdQuery);
		request.setString(personCode);
		request.runQuery();
		int personId = 0;
		if(request.next()) {
			personId = request.getInt("personId");
		} 
		request.endConnection();
		return personId;
	}
	/**
	 * Connects to a SQL database, and returns a person object from the personID
	 * 
	 * @param personId
	 * @return Person
	 */
	public static Person getPerson(int personId) {
		Person p = null;

		SQLFactory conn = new SQLFactory();

		String query = "select personId, personCode, addressId, firstName, lastName, brokerType, secIdentifier from Person p"
				+ "	where personId = ?;";

		conn.startConnection(query);
		conn.setInt(personId);

		conn.runQuery();

		String personCode = null;
		int addressId;
		String firstName = null;
		String lastName = null;
		String brokerType = null;
		String secIdentifier = null;

		if (conn.next()) {
			personCode = conn.getString("personCode");
			addressId = conn.getInt("addressId");
			firstName = conn.getString("firstName");
			lastName = conn.getString("lastName");
			brokerType = conn.getString("brokerType");
			secIdentifier = conn.getString("secIdentifier");
			Address tempAdd = DataLoader.getAddress(addressId);
			ArrayList<String> emailList = DataLoader.getEmails(personId);
			p = new Person(personId, personCode, brokerType, secIdentifier, firstName, lastName, tempAdd, emailList);
		} else {
			throw new IllegalStateException("no such person with personId = " + personId);
		}

		conn.endConnection();
		return p;
	}

	public static ArrayList<Asset> getAllAssets() {
		ArrayList<Asset> assets = new ArrayList<Asset>();
		PrivateInvestment pi = null;
		DepositAccount da = null;
		Stock s = null;

		SQLFactory conn = new SQLFactory();

		String query = "SELECT assetId, assetCode, typeOfAsset, label, quarterlyDividend, baseRateReturn, betaMeasure, stockSymbol, sharePrice, "
				+ "baseOmegaMeasure, totalValue, percentageOwned, sharesOwned, apr FROM Asset;";

		conn.startConnection(query);
		conn.runQuery();

		while (conn.next()) {
			if (conn.getString("typeOfAsset").equals("S")) {
				s = new Stock(conn.getInt("assetId"), conn.getString("assetCode"), conn.getString("typeOfAsset"),
						conn.getString("label"), conn.getDouble("quarterlyDividend"), conn.getDouble("baseRateReturn"),
						conn.getDouble("betaMeasure"), conn.getString("stockSymbol"), conn.getDouble("sharePrice"),
						conn.getInt("sharesOwned"));

				assets.add(s);
			} else if (conn.getString("typeOfAsset").equals("D")) {
				da = new DepositAccount(conn.getInt("assetId"), conn.getString("assetCode"),
						conn.getString("typeOfAsset"), conn.getString("label"), conn.getDouble("apr"),
						conn.getDouble("totalValue"));
				assets.add(da);
			} else if (conn.getString("typeOfAsset").equals("P")) {
				pi = new PrivateInvestment(conn.getInt("assetId"), conn.getString("assetCode"),
						conn.getString("typeOfAsset"), conn.getString("label"), conn.getDouble("quarterlyDividend"),
						conn.getDouble("baseRateReturn"), conn.getDouble("baseOmegaMeasure"),
						conn.getDouble("totalValue"), conn.getDouble("percentageOwned"));
				assets.add(pi);
			}
		}
		conn.endConnection();
		return assets;
	}

	/**
	 * Connects to a SQL database, and returns all AssetIds
	 * 
	 * @return ArrayList of all assetIds
	 */
	public static ArrayList<Integer> getAssetId(int portfolioId) {
		ArrayList<Integer> assetIdList = new ArrayList<Integer>();

		SQLFactory conn = new SQLFactory();

		String query = "select assetId from Portfolio p"
				+ "	left join PortfolioAsset pa on p.portfolioId = pa.portfolioId where p.portfolioId = ?;";

		conn.startConnection(query);
		conn.setInt(portfolioId);
		conn.runQuery();

		while (conn.next()) {
			int assetIdTemp = conn.getInt("assetId");
			assetIdList.add(assetIdTemp);
		}
		conn.endConnection();

		return assetIdList;
	}

	/**
	 * Connects to a SQL database, and returns a portfolio
	 * 
	 * @return a portfolio
	 */
	public static Portfolio getPortfolio(int portfolioId, ArrayList<Asset> fullAssetList,
			ArrayList<Person> peopleList) {
		Portfolio port = null;

		SQLFactory conn = new SQLFactory();

		String query = "select portfolioId, portfolioCode, ownerId, managerId, beneficiaryId from Portfolio"
				+ "	where portfolioId = ?;";

		conn.startConnection(query);
		conn.setInt(portfolioId);
		conn.runQuery();

		String portfolioCode = null;
		int ownerId;
		int managerId;
		int beneficiaryId;
		Person owner = null;
		Person manager = null;
		Person beneficiary = null;
		ArrayList<Integer> assetIdList = null;
		ArrayList<Asset> tempAssetList = new ArrayList<>();

		if (conn.next()) {
			portfolioCode = conn.getString("portfolioCode");
			ownerId = conn.getInt("ownerId");
			managerId = conn.getInt("managerId");
			beneficiaryId = conn.getInt("beneficiaryId");
			assetIdList = DataLoader.getAssetId(portfolioId);

			for (Person bourke : peopleList) {
				if (bourke.getPersonId() == ownerId) {
					owner = bourke;
				}
				if (bourke.getPersonId() == managerId) {
					manager = bourke;
				}
				if (bourke.getPersonId() == beneficiaryId) {
					beneficiary = bourke;
				}
				if (owner != null && manager != null && beneficiary != null) {
					break;
				}
			}

			for (int id : assetIdList) {
				for (Asset ass : fullAssetList) {

					if (ass.getAssetId() == id) {
						tempAssetList.add(ass);
					}
				}
			}

			port = new Portfolio(portfolioId, portfolioCode, owner, manager, beneficiary, tempAssetList);

		}
		conn.endConnection();
		return port;
	}

	/**
	 * Connects to a SQL database, and returns all assets in the form of an
	 * ArrayList of objects (of type private investment, deposit account, or stock)
	 * 
	 * @return ArrayList of all people
	 */
	public static ArrayList<Portfolio> getAllPortfolios(ArrayList<Asset> fullAssetList, ArrayList<Person> peopleList) {
		ArrayList<Portfolio> portList = new ArrayList<>();

		SQLFactory conn = new SQLFactory();

		String query = "select portfolioId, portfolioCode, ownerId, managerId, beneficiaryId from Portfolio;";

		conn.startConnection(query);

		conn.runQuery();

		int portfolioId;
		String portfolioCode = null;
		int ownerId;
		int managerId;
		int beneficiaryId;

		ArrayList<Integer> assetIdList = null;

		while (conn.next()) {
			Person owner = null;
			Person manager = null;
			Person beneficiary = null;
			portfolioId = conn.getInt("portfolioId");
			portfolioCode = conn.getString("portfolioCode");
			ownerId = conn.getInt("ownerId");
			managerId = conn.getInt("managerId");
			beneficiaryId = conn.getInt("beneficiaryId");
			ArrayList<Asset> tempAssetList = new ArrayList<>();
			assetIdList = DataLoader.getAssetId(portfolioId);

			for (Person bourke : peopleList) {
				if (bourke.getPersonId() == ownerId) {
					owner = bourke;
				}
				if (bourke.getPersonId() == managerId) {
					manager = bourke;
				}
				if (bourke.getPersonId() == beneficiaryId) {
					beneficiary = bourke;
				}
				if (owner != null && manager != null && beneficiary != null) {
					break;
				}
			}

			for (int id : assetIdList) {
				for (Asset ass : fullAssetList) {

					if (ass.getAssetId() == id) {
						tempAssetList.add(ass);
					}
				}
			}

			Portfolio port = new Portfolio(portfolioId, portfolioCode, owner, manager, beneficiary, tempAssetList);
			portList.add(port);
		}
		conn.endConnection();
		return portList;
	}

}

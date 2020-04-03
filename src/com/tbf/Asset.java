package com.tbf;

/**
 * This is an abstract class that models a "generic" asset 
 * that the tbf finacial system manages.
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

import java.util.ArrayList;

public abstract class Asset {
	protected String accountCode; 
	protected String assetType;
	protected String label;
	
	public Asset(String accountCode, String assetType, String label) {
		this.accountCode = accountCode;
		this.assetType = assetType;
		this.label = label;
	}
	
	public static Asset getAsset(int assetId) {
        Asset a = null;
        String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

//1. load driver
        try {
            //get declared constructor?
            Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

//2. create connection
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

//        TODO use factory methods for connection?

        //3. formulate your query
        //TODO make sure we get the private investment, stock, or deposit info later
        String query = "SELECT accountCode, assetType, label FROM Person p" + "      where personId = ?;";
        PreparedStatement ps = null;
        ResultSet rs = null;

        String accountCode = null;
        String assetType = null;
        String label = null;

        //4. Execute query
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
                a = new Asset(rs.getString("accountCode"), rs.getString("assetType"), rs.getString("label");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

//5. close resources
        try {
            if (rs != null && rs.isClosed()) {
                rs.close();
            }
            if (ps != null && ps.isClosed()) {
                ps.close();
            }
            if (conn != null && conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return a;
    }


    public static ArrayList<Asset> getAllAssets() {
        ArrayList<Asset> assets = new ArrayList<Asset>();
        Asset a = null;
        String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

//1. load driver
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

            throw new RuntimeException(e);
        }

//2. create connection
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

//        TODO use factory methods for connection?

        //3. formulate your query
        //TODO make sure we get the private investment, stock, or deposit info later
        //TODO make sure SQL is correct
        String query = "SELECT accountCode, assetType, label FROM Asset a;";
        PreparedStatement ps = null;
        ResultSet rs = null;

        //4. Execute query
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            String accountCode = null;
            String assetType = null;
            String label = null;

            while(rs.next()) {
                a = new Asset(rs.getString("accountCode"), rs.getString("assetType"), rs.getString("label");
                assets.add(a);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

//5. close resources
            try {
                if (rs != null && rs.isClosed()) {
                    rs.close();
                }
                if (ps != null && ps.isClosed()) {
                    ps.close();
                }
                if (conn != null && conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception: ");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return assets;
        }

	protected abstract String getAssetType();
	
	public String getLabel() {
		return label;
	}

	public String getAccountCode() {
		return accountCode;
	}
}





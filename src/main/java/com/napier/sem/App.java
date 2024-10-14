package com.napier.sem;

import java.sql.*;

public class App {

    /**
     * Main app method to initialise the application.
     * @param args launching parameters
     */
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();


        // ######## REPORTS BEGIN HERE ######## ///
        try {
            a.report_PopulationDESC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Disconnect from database before termination
        a.disconnect();
    }


    ///////////////////// REPORTS /////////////////////
//<editor-fold desc="SQL Reports">
    public void report_PopulationDESC() throws SQLException {
        ResultSet rs = executeQuery("SELECT name, population FROM country ORDER BY population DESC");
        System.out.println("List of countries by population (from largest to smallest):");
        while (rs.next()) {
            String countryName = rs.getString("name");
            int population = rs.getInt("population");
            System.out.println(countryName + ": " + population);
        }
    }


//</editor-fold>
    ///////////////////// UTILS AND DATABASE CONNECTIONS /////////////////////
//<editor-fold desc="SQL Connection UTILS">
    /**
     * Executes the given SQL query and returns the ResultSet.
     *
     * @param query the SQL query to execute
     * @return the ResultSet of the executed query
     */
    public static ResultSet executeQuery(String query){
        try {
            // Create a statement object to execute the query
            Statement stmt = con.createStatement();
            // Return ResultSet of executed query
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            // Handle SQL exception
            throw new RuntimeException(e);
        }
    }


    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(10000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
//</editor-fold>
}


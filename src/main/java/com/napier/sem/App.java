package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {


    /**
     * Main app method to initialise the application.
     * @param args launching parameters
     */
    public static void main(String[] args) {
        // Create new Application
        App app = new App();

        app.connect();


        // ######## REPORTS BEGIN HERE ######## ///


        // Disconnect from database before termination
        app.disconnect();
    }




    ///////////////////// REPORTS /////////////////////
//<editor-fold desc="SQL Reports">

    public List<City> report_CitiesInWorldDESC() {
        String query = "SELECT * FROM city ORDER BY population DESC";
        return getReport_City(query);
    }


//</editor-fold>
    ///////////////////// UTILS AND DATABASE CONNECTIONS /////////////////////
//<editor-fold desc="SQL Connection UTILS">


    /**
     * Generic method to print ToString of all items of a given List
     * @param items List of ANY objects
     */
    public static <T> void print_Items(List<T> items) {
        if(items != null && !items.isEmpty()) {
            for (Object item : items) {
                System.out.println(item);
            }
        }
    }


    /**
     * Executes the given query and returns a list of Country objects
     *
     * @param query SQL query to execute
     * @return List of Country objects resulting from the query
     */
    public static List<Country> getReport_Country(String query) {
        // Create ArrayList to hold Countries
        List<Country> countries = new ArrayList<>();

        // Execute query
        ResultSet rs = executeQuery(query);
        try {
            // Populate bjects from the ResultSet
            while (rs.next()) {
                // Extract the country details from the ResultSet
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                double surfaceArea = rs.getDouble("SurfaceArea");
                Integer indepYear = rs.getObject("IndepYear") != null ? rs.getInt("IndepYear") : null;
                int population = rs.getInt("Population");
                Double lifeExpectancy = rs.getObject("LifeExpectancy") != null ? rs.getDouble("LifeExpectancy") : null;
                Double gnp = rs.getObject("GNP") != null ? rs.getDouble("GNP") : null;
                Double gnpOld = rs.getObject("GNPOld") != null ? rs.getDouble("GNPOld") : null;
                String localName = rs.getString("LocalName");
                String governmentForm = rs.getString("GovernmentForm");
                String headOfState = rs.getString("HeadOfState");
                Integer capital = rs.getObject("Capital") != null ? rs.getInt("Capital") : null;
                String code2 = rs.getString("Code2");

                // Create Country with extracted data
                Country country = new Country(code, name, continent, region, surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localName, governmentForm, headOfState, capital, code2);

                // Add Country to List
                countries.add(country);
            }
        } catch (Exception ignored) {}

        // Return populated List of Countries
        return countries;
    }

    /**
     * Retrieves a list of cities based on the specified SQL query
     *
     * @param query SQL query to execute for retrieving city records
     * @return A list of City objects populated with data retrieved from the database
     */
    public static List<City> getReport_City(String query) {
        // Create List of Cities
        List<City> cities = new ArrayList<>();

        // Execute query
        ResultSet rs = executeQuery(query);
        try {
            // Populate objects from ResultSet
            while (rs.next()) {
                // Extract city details from ResultSet
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String countryCode = rs.getString("CountryCode");
                String district = rs.getString("District");
                int population = rs.getInt("Population");

                // Create City object
                City city = new City(id, name, countryCode, district, population);

                // Add to List
                cities.add(city);
            }
        } catch (Exception ignored) {}

        return cities;
    }

    /**
     * Retrieves a list of country languages based on the specified SQL query
     *
     * @param query SQL query to execute for retrieving country language records
     * @return A list of CountryLanguage objects populated with data retrieved from the database
     */
    public static List<CountryLanguage> getReport_CountryLanguage(String query) {
        // Create List of CountryLanguages
        List<CountryLanguage> countryLanguages = new ArrayList<>();

        // Execute query
        ResultSet rs = executeQuery(query);
        try {
            // Populate objects from ResultSet
            while (rs.next()) {
                // Extract country-language details from ResultSet
                String countryCode = rs.getString("CountryCode");
                String language = rs.getString("Language");
                String isOfficial = rs.getString("IsOfficial");
                double percentage = rs.getDouble("Percentage");

                // Create CountryLanguage and add to list
                CountryLanguage countryLanguage = new CountryLanguage(countryCode, language, isOfficial, percentage);

                countryLanguages.add(countryLanguage);
            }
        } catch (Exception ignored) {}

        return countryLanguages;
    }



    /**
     * Executes the given SQL query and returns the ResultSet.
     *
     * @param query the SQL query to execute
     * @return the ResultSet of the executed query
     */
    public static ResultSet executeQuery(String query){
        if(con == null) return null;

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
    public static Connection con = null;

    /**
     * Connect to the MySQL database.
     */
//    public void connect() {
//        try {
//            // Load Database driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Could not load SQL driver");
//            System.exit(-1);
//        }
//
//        int retries = 10;
//        for (int i = 0; i < retries; ++i) {
//            System.out.println("Connecting to database...");
//            try {
//                // Wait a bit for db to start
//                Thread.sleep(10000);
//                // Connect to database
//                con = DriverManager.getConnection("jdbc:mysql://" + "localhost:3306" +"/world?useSSL=false", "root", "");
//                System.out.println("Successfully connected");
//                break;
//            } catch (SQLException sqle) {
//                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
//                System.out.println(sqle.getMessage());
//            }
//            catch (InterruptedException ie) {
//                System.out.println("Thread interrupted? Should not happen.");
//            }
//        }
//    }

    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Determine the database host based on the DB_ENV variable
        String dbEnv = System.getenv("DB_ENV"); // e.g., 'local' or 'docker'
        String dbHost;
        String dbPassword = ""; // Assuming you want this empty for the root user

        if ("docker".equalsIgnoreCase(dbEnv)) {
            dbHost = "db"; // Host for Docker
            dbPassword = "example";
        } else {
            dbHost = "localhost"; // Host for local development
            dbPassword = "";

        }

        // Connection parameters
        String dbPort = "3306";
        String dbName = "world";
        String dbUser = "root";

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(10000);
                // Connect to the database using the determined host
                con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useSSL=false", dbUser, dbPassword);
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
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
                System.out.println("Disconnected from database");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
//</editor-fold>
}


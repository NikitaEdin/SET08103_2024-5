package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is class is to run the program and print the reports that are needed.
 */
public class App {

    /**
     * Main app method to initialise the application.
     * @param args launching parameters
     */
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }


        // ######## REPORTS BEGIN HERE ######## ///


        ////// Testing Data and Examples //////

        System.out.println("\nreport_TopN_PopulatedCountries: ");
        print_Items(a.report_TopN_PopulatedCountries(3));
        System.out.println("\nreport_TopN_PopulatedCountriesByContinent: ");
        print_Items(a.report_TopN_PopulatedCountriesByContinent("North America", 4));
        System.out.println("\nreport_TopN_PopulatedCountriesByRegion: ");
        print_Items(a.report_TopN_PopulatedCountriesByRegion("Central Africa", 5));

        System.out.println("\nreport_TopN_PopulatedCities: ");
        print_Items(a.report_TopN_PopulatedCities(3));
        System.out.println("\nreport_TopN_PopulatedCitiesByContinent: ");
        print_Items(a.report_TopN_PopulatedCitiesByContinent("North America", 5));
        System.out.println("\nreport_TopN_PopulatedCitiesByRegion: ");
        print_Items(a.report_TopN_PopulatedCitiesByRegion("Central America", 4));
        System.out.println("\nreport_TopN_PopulatedCitiesByCountry: ");
        print_Items(a.report_TopN_PopulatedCitiesByCountry("United States", 2));
        System.out.println("\nreport_TopN_PopulatedCitiesByDistrict: ");
        print_Items(a.report_TopN_PopulatedCitiesByDistrict("Western", 6));

        System.out.println("\nreport_CitiesInWorldDESC: ");
        print_Items(a.report_CitiesInWorldDESC());
        System.out.println("\nreport_CitiesInContinentDESC: ");
        print_Items(a.report_CitiesInContinentDESC("Africa"));
        System.out.println("\nreport_CitiesInRegionDESC: ");
        print_Items(a.report_CitiesInRegionDESC("Central America"));
        System.out.println("\nreport_CitiesInCountryDESC: ");
        print_Items(a.report_CitiesInCountryDESC("Angola"));
        System.out.println("\nreport_CitiesInDistrictDESC: ");
        print_Items(a.report_CitiesInDistrictDESC("Scotland"));

        System.out.println("\nreport_CapitalCitiesInWorldDESC: ");
        print_Items_Capitals(a.report_CapitalCitiesInWorldDESC());
        System.out.println("\nreport_CapitalCitiesInContinentDESC: ");
        print_Items_Capitals(a.report_CapitalCitiesInContinentDESC("North America"));
        System.out.println("\nreport_CapitalCitiesInRegionDESC: ");
        print_Items_Capitals(a.report_CapitalCitiesInRegionDESC("Western Europe"));



        // Disconnect from database before termination
        a.disconnect();
    }


    ///////////////////// REPORTS /////////////////////
//<editor-fold desc="SQL Reports">


    /**
     * All the countries in the world organised by largest population to smallest
     * @return Return a list of Country type in DESC order
     */
    public List<Country> report_PopulationDESC() {
        String query = "SELECT * FROM country ORDER BY population DESC";
        return getReport_Country(query);
    }

    /**
     * All the countries in a continent organised by largest population to smallest
     * @param continent Continent name to filter by
     * @return  Return a list of Country type in DESC order
     */
    public List<Country> report_PopulationByContinentDESC(String continent) {
        if(continent != null && !continent.isEmpty()){
            String query = "SELECT * FROM country WHERE continent = '" + continent + "' ORDER BY population DESC";
            return getReport_Country(query);
        }
        else{
            System.out.println("Invalid continent");
            return null;
        }

    }

    /**
     * All the countries in a region organised by largest population to smallest
     * @param region Region name to filter by
     * @return Returns a list of Country type in DESC order
     */
    public List<Country> report_CountriesByRegionDESC(String region) {
        if (region !=null && !region.isEmpty()){
            String query = "SELECT * FROM country WHERE region = '"+ region +"' ORDER BY population DESC";
            return getReport_Country(query);
        }
        System.out.println("Invalid region");
        return null;
    }


    /**
     * The top N populated countries in the world where N is provided by the user
     * @param N Number of top populated countries to retrieve
     * @return A list of the top N populated countries, sorted in descending order by population
     */
    public List<Country> report_TopN_PopulatedCountries(int N){
        if (N < 1) return null;
        return getReport_Country("SELECT * FROM country ORDER BY population DESC LIMIT "+ N);
    }

    /**
     * The top N populated countries in a continent where N is provided by the user
     * @param continent Name of the continent to filter the countries by
     * @param N The number of top populated countries to retrieve for the specified continent
     * @return List of top N populated countries in the specified continent
     */
    public List<Country> report_TopN_PopulatedCountriesByContinent(String continent, int N) {
        if( N < 1 || continent.isEmpty()) return null;
        String query = "SELECT * FROM country WHERE continent = '"+ continent +"' ORDER BY population DESC LIMIT "+ N;
        return getReport_Country(query);
    }

    /**
     * The top N populated countries in a region where N is provided by the user
     * @param region Name of the region to filter the countries by
     * @param N Number of top populated countries to retrieve for the specified region
     * @return A list of top N populated countries in the specified region
     */
    public List<Country> report_TopN_PopulatedCountriesByRegion(String region, int N) {
        if( N < 1 || region.isEmpty()) return null;
        String query = "SELECT * FROM country WHERE region = '" + region + "' ORDER BY population DESC LIMIT "+ N;
        return getReport_Country(query);
    }

    /**
     * The top N populated cities in the world where N is provided by the user.
     * @param N Number of top populated cities to retrieve
     * @return List of top N populated cities, sorted in descending order by population
     */
    public List<City> report_TopN_PopulatedCities(int N) {
        if (N < 1) return null;
        String query = "SELECT * FROM city ORDER BY population DESC LIMIT " + N;
        return getReport_City(query);
    }

    /**
     * The top N populated cities in a continent where N is provided by the user.
     * @param continent Name of the continent to filter the cities by
     * @param N Number of top populated cities to retrieve
     * @return List of top N populated cities in the specified continent, sorted in descending order by population
     */
    public List<City> report_TopN_PopulatedCitiesByContinent(String continent, int N) {
        if (N < 1 || continent.isEmpty()) return null;
        String query = "SELECT city.* FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Continent = '" + continent + "' ORDER BY city.Population DESC LIMIT " + N;
        return getReport_City(query);
    }


    /**
     * The top N populated cities in a region where N is provided by the user.
     * @param region Name of the region to filter the cities by
     * @param N Number of top populated cities to retrieve
     * @return List of top N populated cities in specified region, sorted in descending order by population
     */
    public List<City> report_TopN_PopulatedCitiesByRegion(String region, int N) {
        if (N < 1 || region.isEmpty()) return null;
        String query = "SELECT city.* FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Region = '" + region + "' ORDER BY city.Population DESC LIMIT " + N;
        return getReport_City(query);
    }

    /**
     * The top N populated cities in a country where N is provided by the user.
     * @param country Name of the country to filter the cities by
     * @param N Number of top populated cities to retrieve
     * @return List of top N populated cities in specified country, sorted in descending order by population
     */
    public List<City> report_TopN_PopulatedCitiesByCountry(String country, int N) {
        if (N < 1 || country.isEmpty()) return null;
        String query = "SELECT city.* FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Name = '" + country + "' ORDER BY city.Population DESC LIMIT " + N;
        return getReport_City(query);
    }

    /**
     * The top N populated cities in a district where N is provided by the user.
     * @param district Name of district to filter the cities by
     * @param N Number of top populated cities to retrieve
     * @return List of top N populated cities in specified district, sorted in descending order by population
     */
    public List<City> report_TopN_PopulatedCitiesByDistrict(String district, int N) {
        if (N < 1 || district.isEmpty()) return null;
        String query = "SELECT * FROM city WHERE District = '" + district + "' ORDER BY Population DESC LIMIT " + N;
        return getReport_City(query);
    }

    /**
     * All the cities in the world organised by largest population to smallest.
     * @return List of all cities in the world, sorted in descending order by population.
     */
    public List<City> report_CitiesInWorldDESC() {
        String query = "SELECT * FROM city ORDER BY population DESC";
        return getReport_City(query);
    }

    /**
     * All the cities in a continent organised by largest population to smallest.
     * @param continent Name of continent to filter the cities by.
     * @return List of cities in specified continent, sorted by descending order by population.
     */
    public List<City> report_CitiesInContinentDESC(String continent) {
        if(continent.isEmpty()) return null;
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Continent = '" + continent + "' ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the cities in a region organised by largest population to smallest.
     * @param region Name of region to filter the cities by.
     * @return List of cities in specified region, sorted by descending order by population.
     */
    public List<City> report_CitiesInRegionDESC(String region) {
        if(region.isEmpty()) return null;
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Region = '" + region + "' ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the cities in a country organised by largest population to smallest.
     * @param country Name of country to filter the cities by.
     * @return List of cities in the specified country, sorted by descending order by population.
     */
    public List<City> report_CitiesInCountryDESC(String country) {
        if(country.isEmpty()) return null;
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Name = '" + country + "' ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the cities in a district organised by largest population to smallest.
     * @param district Name of district to filter the cities by.
     * @return List of cities in the specified country, sorted by descending order by population.
     */
    public List<City> report_CitiesInDistrictDESC(String district) {
        if(district.isEmpty()) return null;
        String query = "SELECT * FROM city WHERE District = '" + district + "' ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the capital cities in the world organised by largest population to smallest.
     * @return List of all capital cities in the world, sorted by descending order by population.
     */
    public List<City> report_CapitalCitiesInWorldDESC() {
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Capital = city.ID ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the capital cities in a continent organised by largest population to smallest.
     * @param continent Name of district to filter the cities by.
     * @return List of capital cities in the specified continent, sorted by descending order by population.
     */
    public List<City> report_CapitalCitiesInContinentDESC(String continent) {
        if(continent.isEmpty()) return null;
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Continent = '" + continent + "' AND country.Capital = city.ID ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the capital cities in a region organised by largest to smallest.
     * @param region Name of region to filter the cities by.
     * @return List of capital cities in the specified region, sorted by descending order by population.
     */
    public List<City> report_CapitalCitiesInRegionDESC(String region) {
        if(region.isEmpty()) return null;
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Region = '" + region + "' AND country.Capital = city.ID ORDER BY city.population DESC";
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
     * Method to print specific columns for capital city reports.
     * @param cityList List of capital cities to be printed to screen.
     */
    public static void print_Items_Capitals(List<City> cityList){
        if(cityList != null && !cityList.isEmpty()) {
            for (City city : cityList) {
                System.out.println("City{" +
                                    "Name='" + city.Name + '\'' +
                                    "CountryCode='" + city.CountryCode + '\'' +
                                    ", Population='" + city.Population + '}');
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

    public static void setConnection(Connection connection) {
        con = connection;
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        if (location != null) {
            try {
                // Load Database driver
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Could not load SQL driver");
                System.exit(-1);
            }

            int retries = 10;
            boolean shouldWait = false;
            for (int i = 0; i < retries; ++i) {
                System.out.println("Connecting to database...");
                try {
                    if (shouldWait) {
                        // Wait a bit for db to start
                        Thread.sleep(delay);
                    }

                    // Connect to database
                    con = DriverManager.getConnection("jdbc:mysql://" + location
                                    + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                            "root", "example");
                    System.out.println("Successfully connected");
                    break;
                } catch (SQLException sqle) {
                    System.out.println("Failed to connect to database attempt " + i);
                    System.out.println(sqle.getMessage());

                    // Let's wait before attempting to reconnect
                    shouldWait = true;
                } catch (InterruptedException ie) {
                    System.out.println("Thread interrupted? Should not happen.");
                }
            }
        }
        else {
            System.out.println("Failed to connect to database");
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


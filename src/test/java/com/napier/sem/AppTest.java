package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {

    static App app;

    /** Development config variable, to switch on/off DB connection,
     * will skip data dependent tests if set to false */
    private final static boolean CONFIG_useDatabase = true;


    @BeforeAll
    /**
     * This initialises the App before all tests in this class are started.
     */
    static void setUpBeforeClass(){
        if (CONFIG_useDatabase){
            app = new App();
            if(App.con != null){
                app.disconnect();
            }
            app.connect();
        }
    }

    @AfterAll
    /**
     * Deconstructor to free memory and disconnect databse connections.
     */
    static void tearDownAfterClass(){
        if(app != null){
            app.disconnect();
        }
    }

    ///////////////////// Database Tests /////////////////////
    //<editor-fold desc="Database Tests">


    /**
     * Unit test to verify CitiesInWorldDESC report output is valid and as expected
     */
    @Test
    public void test_report_CitiesInWorldDESC(){
        if(!CONFIG_useDatabase || App.con == null) return;

        List<City> items = app.report_CitiesInWorldDESC();
        // Not null
        assertNotNull(items);
        // Expected size
        assertEquals(items.size(), 4079);
        // Expected item details of index 0
        assertEquals(10500000, items.get(0).Population);
        assertEquals("Maharashtra", items.get(0).District);
        assertEquals("IND", items.get(0).CountryCode);
        assertEquals("Mumbai (Bombay)", items.get(0).Name);
    }

    //</editor-fold>

    ///////////////////// Country Tests /////////////////////
    //<editor-fold desc="Country Tests">

    /**
     *
     * @param countryList is a list of countries from the Country class
     * @return Returns a list of country objects
     */
    public static List<Country> getCountryList(List<Country> countryList) {
        List<Country> countries = new ArrayList<>();
        for (Country country : countryList) {
            if (country != null) {
                countries.add(country);
            }
        }
        return countries;
    }

    /**
     * Method to test the getReport_Country method by  creating test data and null test data to see how the method handles it.
     */
    @Test
    void getReport_Country(){
        List<Country> testData = new ArrayList<Country>();
        // Test case for America
        testData.add(new Country("USA","United States","North America","North America",9987652.00,1776,345426571,77.5,21477426.0,20611819.0,"United States","Democracy","President",1,"US"));
        // Test if no country is given
        testData.add(null);
        // Test case for Spain
        testData.add(new Country("ESP", "Spain", "Europe", "Southern Europe",
                505992.0, 1479, 47450795, 83.6, 1419043.0, 1263782.0,
                "Espana", " Monarchy", "King", 3, "ES"));

        // Create a new list with the test data
        List<Country> result = getCountryList(testData);

        // Test if the test data is not null
        assertNotNull(result);
        // Check that there is two countries created
        assertEquals(2,result.size());

        // Check that the test data is the same
        assertEquals("USA",result.get(0).getCode());
        assertEquals("ESP",result.get(1).getCode());
    }
    @Test
    /**
     * Tests the print_Items method to ensure it handles an empty list without errors.
     */
    void print_ItemsCountryContainsNull(){
        // Create ArrayList
        ArrayList<Country> countries = new ArrayList<Country>();
        // Add null into the ArrayList
        countries.add(null);
        // Call print_Items Method
        app.print_Items(countries);
    }

    @Test
    /**
     * This tests the print_Items method to test if any errors occur if its provided a empty list
     */
    void print_ItemsCountryContainsEmpty(){
        // Create ArrayList
        ArrayList<Country> countries = new ArrayList<Country>();
        // Call print_Items method
        app.print_Items(countries);
    }

    @Test
    /**
     *  This test is to test the method print_Items if it functions without errors with normal data.
     */
    void print_ItemsCountry(){
        // Create the Country object using the constructor
        Country c = new Country(
                "123", "Scotland", "Europe", "Rainy", 300.0,
                0, 5000000, null, 867.518, 600.629, "Scotland",
                "Government Form", "King", 1, "456"
        );
        // Create ArrayList to store the info
        ArrayList<Country> countries = new ArrayList<>();
        // Add test data to the ArrayList
        countries.add(c);
        // Use print_items method
        app.print_Items(countries);

    }
    //</editor-fold>

    ///////////////////// City Tests /////////////////////
    //<editor-fold desc="City Tests">

    /**
     *
     * @param cityList is a list of cities
     * @return returns a list of cities that are not null
     */
    public static List<City> getCityList(List<City> cityList) {
        List<City> city = new ArrayList<>();
        for (City cities : cityList) {
            if (cities != null) {
                city.add(cities);
            }
        }
        return city;
    }

    /**
     *  Tests the method getReport_City method to test if it causes errors when no city is provided,
     *  and to check that the data provided returns the same when once inputted.
     */
    void getReport_City(){
        List<City> testData = new ArrayList<City>();
        // Test case for New York
        testData.add(new City(1,"New York","USA","District",19571216));
        // Test if no city is given
        testData.add(null);
        // Test case for Edinburgh
        testData.add(new City(2,"Edinburgh","SCO","District",558676));

        // Create a new list with the test data
        List<City> result = getCityList(testData);

        // Test if the test data is not null
        assertNotNull(result);
        // Check that there is two countries created
        assertEquals(2,result.size());

        // Check that the test data is the same
        assertEquals(1,result.get(0).getID());
        assertEquals(2,result.get(1).getID());
    }


    @Test
    /**
     * Tests the print_Items method to ensure it handles an empty list without errors.
     */
    void print_ItemsCityContainsNull(){
        // Create ArrayList
        ArrayList<City> cities = new ArrayList<City>();
        // Add null into the ArrayList
        cities.add(null);
        // Call print_Items Method
        app.print_Items(cities);
    }

    @Test
    /**
     * This tests the print_Items method to test if any errors occur if its provided a empty list
     */
    void print_ItemsCitiesContainsEmpty(){
        // Create ArrayList
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        // Call print_Items method
        app.print_Items(cities);
    }

    @Test
    /**
     *  This test is to test the method print_Items if it functions without errors with normal data.
     */
    void print_ItemsCity(){
        // Create the Country object using the constructor
        City c = new City(
                001,"Edinburgh","123","District",1000000
        );
        // Create ArrayList to store the info
        ArrayList<City> cities = new ArrayList<>();
        // Add test data to the ArrayList
        cities.add(c);
        // Use print_items method
        app.print_Items(cities);

    }
    //</editor-fold>

    ///////////////////// Country Language Tests /////////////////////
    //<editor-fold desc="Country Language Tests">
    @Test
    /**
     * Tests the print_Items method to ensure it handles an empty list without errors.
     */

    void print_ItemsCountryLanguageContainsNull(){
        // Create ArrayList
        ArrayList<CountryLanguage> countryLanguages = new ArrayList<CountryLanguage>();
        // Add null into the ArrayList
        countryLanguages.add(null);
        // Call print_Items Method
        app.print_Items(countryLanguages);
    }

    @Test
    /**
     * This tests the print_Items method to test if any errors occur if its provided a empty list
     */
    void print_ItemsCountryLanguageContainsEmpty(){
        // Create ArrayList
        ArrayList<CountryLanguage> countryLanguages = new ArrayList<CountryLanguage>();
        countryLanguages.add(null);
        // Call print_Items method
        app.print_Items(countryLanguages);
    }

    @Test
    /**
     *  This test is to test the method print_Items if it functions without errors with normal data.
     */
    void print_ItemsCountryLanguage(){
        // Create the Country object using the constructor
        CountryLanguage c = new CountryLanguage(
                "4321","English","T",90.00
        );
        // Create ArrayList to store the info
        ArrayList<CountryLanguage> countryLanguages = new ArrayList<>();
        // Add test data to the ArrayList
        countryLanguages.add(c);
        // Use print_items method
        app.print_Items(countryLanguages);

    }
    //</editor-fold>
}

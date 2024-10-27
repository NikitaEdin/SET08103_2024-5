package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AppTest {

    static App app;

    @BeforeAll
    /**
     * This initialises the App before all tests in this class are started.
     */
    static void setUpBeforeClass(){
        app = new App();
    }

    ///////////////////// Database Tests /////////////////////
    //<editor-fold desc="Database Tests">



    //</editor-fold>

    ///////////////////// Country Tests /////////////////////
    //<editor-fold desc="Country Tests">
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

package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class testing the App class methods.
 */
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
     * Method to test the report_PopulationByContinentDESC method to make sure it can handle an empty continent being passed in.
     */
    @Test
    void testReport_PopulationByContinentDESC_emptyContinent(){
        String continent = "";
        assertNull(app.report_PopulationByContinentDESC(continent));
    }

    /**
     * Method to test the report_CountriesByRegionDESC method to make sure it can handle an empty region being passed in.
     */
    @Test
    void testReport_CountriesByRegionDESC_emptyRegion(){
        String region = "";
        assertNull(app.report_CountriesByRegionDESC(region));
    }

    /**
     * Method to test the report_TopN_PopulatedCountries method with an N value less than 1 being passed in.
     */
    @Test
    void testReport_TopN_PopulatedCountries_NLessThan1(){
        int n = 0;
        assertNull(app.report_TopN_PopulatedCountries(n));
    }

    /**
     * Method to test the report_TopN_PopulatedCountriesByContinent method with an N value less than 1 being passed in.
     */
    @Test
    void testReport_TopN_PopulatedCountriesByContinent_NLessThan1(){
        int n = 0;
        String continent = "Africa";
        assertNull(app.report_TopN_PopulatedCountriesByContinent(continent,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCountriesByContinent method to make sure it can handle an empty continent being passed in.
     */
    @Test
    void testReport_TopN_PopulatedCountriesByContinent_continentEmpty(){
        int n = 3;
        String continent = "";
        assertNull(app.report_TopN_PopulatedCountriesByContinent(continent,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCountriesByContinent method to make sure it can handle an empty continent being passed in
     * as well as an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCountriesByContinent_continentEmptyAndNLessThan1(){
        int n = 0;
        String continent = "";
        assertNull(app.report_TopN_PopulatedCountriesByContinent(continent,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCountriesByRegion method with an N value less than 1 being passed in.
     */
    @Test
    void testReport_TopN_PopulatedCountriesByRegion_NLessThan1(){
        int n = 0;
        String region = "Central America";
        assertNull(app.report_TopN_PopulatedCountriesByRegion(region,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCountriesByRegion method to make sure it can handle an empty region being passed in.
     */
    @Test
    void testReport_TopN_PopulatedCountriesByRegion_regionEmpty(){
        int n = 3;
        String region = "";
        assertNull(app.report_TopN_PopulatedCountriesByRegion(region,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCountriesByRegion method to make sure it can handle an empty region being passed in
     * as well as an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCountriesByRegion_regionEmptyAndNLessThan1(){
        int n = 0;
        String region = "";
        assertNull(app.report_TopN_PopulatedCountriesByRegion(region,n));
    }

    /**
     * Method to test the getReport_Country method by  creating test data and null test data to see how the method handles it.
     */
    @Test
    void testGetReport_Country(){
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
    void testPrint_ItemsCountryContainsNull(){
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
    void testPrint_ItemsCountryContainsEmpty(){
        // Create ArrayList
        ArrayList<Country> countries = new ArrayList<Country>();
        // Call print_Items method
        app.print_Items(countries);
    }

    @Test
    /**
     *  This test is to test the method print_Items if it functions without errors with normal data.
     */
    void testPrint_ItemsCountry(){
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
     * Method to test the report_TopN_PopulatedCities method with an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCities_NLessThan1(){
        int n = 0;
        assertNull(app.report_TopN_PopulatedCities(n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByContinent method with an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByContinent_NLessThan1(){
        int n = 0;
        String continent = "Africa";
        assertNull(app.report_TopN_PopulatedCitiesByContinent(continent,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByContinent method to make sure it can handle an empty continent being passed in.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByContinent_continentEmpty(){
        int n = 3;
        String continent = "";
        assertNull(app.report_TopN_PopulatedCitiesByContinent(continent,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByContinent method to make sure it can handle an empty continent being passed in
     * as well as an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByContinent_continentEmptyAndNLessThan1(){
        int n = 0;
        String continent = "";
        assertNull(app.report_TopN_PopulatedCitiesByContinent(continent,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByRegion method with an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByRegion_NLessThan1(){
        int n = 0;
        String region = "Central America";
        assertNull(app.report_TopN_PopulatedCitiesByRegion(region,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByRegion method to make sure it can handle an empty region being passed in.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByRegion_regionEmpty(){
        int n = 3;
        String region = "";
        assertNull(app.report_TopN_PopulatedCitiesByRegion(region,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByRegion method to make sure it can handle an empty region
     * as well as an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByRegion_regionEmptyAndNLessThan1(){
        int n = 0;
        String region = "";
        assertNull(app.report_TopN_PopulatedCitiesByRegion(region,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByCountry method with an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByCountry_NLessThan1(){
        int n = 0;
        String country = "Spain";
        assertNull(app.report_TopN_PopulatedCitiesByCountry(country,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByCountry method to make sure it can handle an empty country being passed in.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByCountry_countryEmpty(){
        int n = 3;
        String country = "";
        assertNull(app.report_TopN_PopulatedCitiesByCountry(country,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByCountry method to make sure it can handle an empty country
     * as well as an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByCountry_countryEmptyAndNLessThan1(){
        int n = 0;
        String country = "";
        assertNull(app.report_TopN_PopulatedCitiesByCountry(country,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByDistrict method with an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByDistrict_NLessThan1(){
        int n = 0;
        String district = "Scotland";
        assertNull(app.report_TopN_PopulatedCitiesByDistrict(district,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByDistrict method to make sure it can handle an empty district being passed in.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByDistrict_districtEmpty(){
        int n = 3;
        String district = "";
        assertNull(app.report_TopN_PopulatedCitiesByDistrict(district,n));
    }

    /**
     * Method to test the report_TopN_PopulatedCitiesByDistrict method to make sure it can handle an empty district
     * as well as an N value less than 1.
     */
    @Test
    void testReport_TopN_PopulatedCitiesByDistrict_districtEmptyAndNLessThan1(){
        int n = 0;
        String district = "";
        assertNull(app.report_TopN_PopulatedCitiesByDistrict(district,n));
    }

    /**
     * Method to test the report_CitiesInContinentDESC method to make sure it can handle an empty continent being passed in.
     */
    @Test
    void testReport_CitiesInContinentDESC_continentEmpty(){
        String continent = "";
        assertNull(app.report_CitiesInContinentDESC(continent));
    }

    /**
     * Method to test the report_CitiesInRegionDESC method to make sure it can handle an empty region being passed in.
     */
    @Test
    void testReport_CitiesInRegionDESC_regionEmpty(){
        String region = "";
        assertNull(app.report_CitiesInRegionDESC(region));
    }

    /**
     * Method to test the report_CitiesInCountryDESC method to make sure it can handle an empty country being passed in.
     */
    @Test
    void testReport_CitiesInCountryDESC_countryEmpty(){
        String country = "";
        assertNull(app.report_CitiesInCountryDESC(country));
    }

    /**
     * Method to test the report_CitiesInDistrictDESC method to make sure it can handle an empty district being passed in.
     */
    @Test
    void testReport_CitiesInDistrictDESC_districtEmpty(){
        String district = "";
        assertNull(app.report_CitiesInDistrictDESC(district));
    }

    /**
     * Method to test the report_CapitalCitiesInContinentDESC method to make sure it can handle an empty continent being passed in.
     */
    @Test
    void testReport_CapitalCitiesInContinentDESC_continentEmpty(){
        String continent = "";
        assertNull(app.report_CapitalCitiesInContinentDESC(continent));
    }

    /**
     * Method to test the report_CapitalCitiesInRegionDESC method to make sure it can handle an empty region being passed in.
     */
    @Test
    void testReport_CapitalCitiesInRegionDESC_regionEmpty(){
        String region = "";
        assertNull(app.report_CapitalCitiesInRegionDESC(region));
    }

    // Tests for City reports still to be implemented.
//
//    /**
//     * Method to test the report_TopN_PopulatedCapitalCitiesInWorld method with an N value less than 1.
//     */
//    @Test
//    void testReport_TopN_PopulatedCapitalCitiesInWorld_NLessThan1(){
//        int n = 0;
//        assertNull(app.report_TopN_PopulatedCapitalCitiesInWorld(n));
//    }
//
//    /**
//     * Method to test the report_TopN_PopulatedCapitalCitiesInContinent method with an N value less than 1.
//     */
//    @Test
//    void testReport_TopN_PopulatedCapitalCitiesInContinent_NLessThan1(){
//        int n = 0;
//        String continent = "Africa";
//        assertNull(app.report_TopN_PopulatedCapitalCitiesInContinent(n, continent));
//    }
//
//    /**
//     * Method to test the report_TopN_PopulatedCapitalCitiesInContinent method to make sure it can handle an empty continent being passed in.
//     */
//    @Test
//    void testReport_TopN_PopulatedCapitalCitiesInContinent_continentEmpty(){
//        int n = 3;
//        String continent = "";
//        assertNull(app.report_TopN_PopulatedCapitalCitiesInContinent(n, continent));
//    }
//
//    /**
//     * Method to test the report_TopN_PopulatedCapitalCitiesInContinent method to make sure it can handle an empty continent being passed in
//     * as well as an N value less than 1.
//     */
//    @Test
//    void testReport_TopN_PopulatedCapitalCitiesInContinent_continentEmptyAndNLessThan1(){
//        int n = 0;
//        String continent = "";
//        assertNull(app.report_TopN_PopulatedCapitalCitiesInContinent(n, continnt));
//    }
//
//    /**
//     * Method to test the report_TopN_PopulatedCapitalCitiesInRegion method with an N value less than 1.
//     */
//    @Test
//    void testReport_TopN_PopulatedCapitalCitiesInRegion_NLessThan1(){
//        int n = 0;
//        String region = "Central America";
//        assertNull(app.report_TopN_PopulatedCapitalCitiesInRegion(n, region));
//    }
//
//    /**
//     * Method to test the report_TopN_PopulatedCapitalCitiesInRegion method to make sure it can handle an empty region being passed in.
//     */
//    @Test
//    void testReport_TopN_PopulatedCapitalCitiesInRegion_regionEmpty(){
//        int n = 3;
//        String region = "";
//        assertNull(app.report_TopN_PopulatedCapitalCitiesInRegion(n, region));
//    }
//
//    /**
//     * Method to test the report_TopN_PopulatedCapitalCitiesInRegion method to make sure it can handle an empty region being passed in
//     * as well as an N value less than 1.
//     */
//    @Test
//    void testReport_TopN_PopulatedCapitalCitiesInRegion_regionEmptyAndNLessThan1(){
//        int n = 0;
//        String region = "";
//        assertNull(app.report_TopN_PopulatedCapitalCitiesInRegion(n, region));
//    }

    @Test
    /**
     *  Tests the method getReport_City method to test if it causes errors when no city is provided,
     *  and to check that the data provided returns the same when once inputted.
     */
    void testGetReport_City(){
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
    void testPrint_ItemsCityContainsNull(){
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
    void testPrint_ItemsCitiesContainsEmpty(){
        // Create ArrayList
        ArrayList<City> cities = new ArrayList<City>();
        // Call print_Items method
        app.print_Items(cities);
    }

    @Test
    /**
     *  This test is to test the method print_Items if it functions without errors with normal data.
     */
    void testPrint_ItemsCity(){
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

    @Test
    /**
     *  This is to test the method print_Items_Capitals and this is its normal use case
     */
    void testPrint_Items_Capitals(){
        City capital = new City(
                002,"Paris","432","District",1500000
        );
        ArrayList<City> capitals = new ArrayList<>();
        capitals.add(capital);
        app.print_Items_Capitals(capitals);
    }

    @Test
    /**
     * This is to test the print_Items_Capitals method and to see if it can handle null being provided
     */
    void testPrint_Items_CapitalsNull(){
        ArrayList<City> capitals = null;
        app.print_Items_Capitals(capitals);
    }

    @Test
    /**
     * This is to test the print_Items_Capitals method to see if it can handle no data being provided.
     */
    void testPrint_Items_CapitalsEmpty(){
        ArrayList<City> capitals = new ArrayList<>();
        app.print_Items_Capitals(capitals);
    }
    //</editor-fold>

    ///////////////////// Total Population Tests /////////////////////
    //<editor-fold desc="Total Population Tests">

    // Tests for Total Population reports still to be implemented.
//
//    /**
//     * Method to test the report_TotalPopulation_Continent method to make sure it can handle an empty continent being passed in.
//     */
//    @Test
//    void testReport_TotalPopulation_Continent_continentEmpty(){
//        String continent = "";
//        assertNull(app.report_TotalPopulation_Continent(continent));
//    }
//
//    /**
//     * Method to test the report_TotalPopulation_Region method to make sure it can handle an empty region being passed in.
//     */
//    @Test
//    void testReport_TotalPopulation_Region_regionEmpty(){
//        String region = "";
//        assertNull(app.report_TotalPopulation_Region(region));
//    }
//
//    /**
//     * Method to test the report_TotalPopulation_Country method to make sure it can handle an empty country being passed in.
//     */
//    @Test
//    void testReport_TotalPopulation_Country_countryEmpty(){
//        String country = "";
//        assertNull(app.report_TotalPopulation_Country(country));
//    }
//
//    /**
//     * Method to test the report_TotalPopulation_District method to make sure it can handle an empty district being passed in.
//     */
//    @Test
//    void testReport_TotalPopulation_District_districtEmpty(){
//        String district = "";
//        assertNull(app.report_TotalPopulation_District(district));
//    }
//
//    @Test
//    void testReport_TotalPopulation_City_cityEmpty(){
//        String city = "";
//        assertNull(app.report_TotalPopulation_City(city));
//    }

    //</editor-fold>

    ///////////////////// Country Language Tests /////////////////////
    //<editor-fold desc="Country Language Tests">


    //</editor-fold>
}

package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;
    static Connection actualCon;

    /**  Method to initialise required logic before the tests are executed */
    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);

        // Save reference to actual connection
        actualCon = App.con;
    }

    @AfterEach
    void tearDown() {
        // Restore original connection after each test
        App.con = actualCon;
    }

    @AfterAll
    static void close() {
        App.con = actualCon;
        app.disconnect();
    }

    /**
     * Tests the report_TopN_PopulatedCountries method to ensure it returns the top N populated Countries.
     * sorted in descending order by population.
     */
    @Test
    void test_TopN_PopulatedCountries(){
        List<Country> countries = app.report_TopN_PopulatedCountries(3);

        // Not Null
        assertNotNull(countries, "The result should not be null");

        // Exact value of items
        assertEquals(3, countries.size(), "There should be 3 countries returned");

        // Verify countries are in descending order
        for (int i = 0; i < countries.size() - 1; i++) {
            assertTrue(countries.get(i).Population >= countries.get(i + 1).Population,
                    "Countries should be ordered in descending population");
        }
    }

    /**
     * Test method TopN_PopulateCountries to check if it can handle a null parameter
     */
    @Test
    void test_nullInvalid_TopN_PopulatedCountries(){
        List<Country> countries = app.report_TopN_PopulatedCountries(0);
        assertNull(countries, "The result should be null");
        countries = app.report_TopN_PopulatedCountries(-2);
        assertNull(countries, "The result should be null");
    }

    /**
     * Tests the report_TopN_PopulatedCountriesByContinent method to ensure it returns the top N populated Countries in a continent.
     * sorted in descending order by population.
     */
    @Test
    void test_TopN_PopulatedCountriesByContinent(){
        List<Country> countries = app.report_TopN_PopulatedCountriesByContinent("North America", 5);

        // Not Null
        assertNotNull(countries, "The result should not be null");

        // Exact value of items
        assertEquals(5, countries.size(), "There should be 5 countries returned");

        // Verify countries are in descending order
        for (int i = 0; i < countries.size() - 1; i++) {
            assertTrue(countries.get(i).Population >= countries.get(i + 1).Population,
                    "Countries should be ordered in descending population for the specified continent");
        }
    }

    /**
     * Test method TopN_PopulateCountriesByContinent to check if it can handle null parameters
     */
    @Test
    void test_nullEmpty_TopN_PopulatedCountriesByContinent(){
        List<Country> countries = app.report_TopN_PopulatedCountriesByContinent("",0);
        assertNull(countries, "The result should be null");
        countries = app.report_TopN_PopulatedCountriesByContinent(null,0);
        assertNull(countries, "The result should be null");
    }

    /**
     * Test method for TopN_PopulatedCountriesByContinent to check if it can handle invalid inputs in its parameters
     */
    @Test
    void test_invalid_TopN_PopulatedCountriesByContinent(){
        List<Country> countries = app.report_TopN_PopulatedCountriesByContinent("Central Europe", -2);
        assertNull(countries, "The result should be null");
    }

    /**
     * Tests the report_TopN_PopulatedCountriesByRegion method to ensure it returns the top N populated Countries in a region.
     * sorted in descending order by population.
     */
    @Test
    void test_TopN_PopulatedCountriesByRegion(){
        List<Country> countries = app.report_TopN_PopulatedCountriesByRegion("Central Africa", 5);

        // Not Null
        assertNotNull(countries, "The result should not be null");

        // Exact value of items
        assertEquals(5, countries.size(), "There should be 5 countries returned");

        // Verify countries are in descending order
        for (int i = 0; i < countries.size() - 1; i++) {
            assertTrue(countries.get(i).Population >= countries.get(i + 1).Population,
                    "Countries should be ordered in descending population for the specified region");
        }
    }

    /**
     * Test method TopN_PopulateCountriesByRegion to check if it can handle null parameters
     */
    @Test
    void test_nullEmpty_TopN_PopulatedCountriesByRegion(){
        List<Country> countries = app.report_TopN_PopulatedCountriesByRegion("",0);
        assertNull(countries, "The result should be null");
        countries = app.report_TopN_PopulatedCountriesByRegion(null,0);
        assertNull(countries, "The result should be null");
    }

    /**
     * Test method for TopN_PopulatedCountriesByContinent to check if it can handle invalid inputs in its parameters
     */
    @Test
    void test_invalid_TopN_PopulatedCountriesByRegion(){
        List<Country> countries = app.report_TopN_PopulatedCountriesByRegion("Central Europe", -2);
        assertNull(countries, "The result should be null");
    }

    /**
     * Tests the report_TopN_PopulatedCities method to ensure it returns the top N populated cities.
     * sorted in descending order by population.
     */
    @Test
    void test_TopN_PopulatedCities(){
        List<City> cities = app.report_TopN_PopulatedCities(3);

        // Not Null
        assertNotNull(cities, "The result should not be null");

        // Exact value of items
        assertEquals(3, cities.size(), "There should be 3 cities returned");

        // Verify cities are in descending order
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population.");
        }
    }

    /**
     * Test method TopN_PopulatedCities to check if it can handle a null parameter
     */
    @Test
    void test_nullInvalid_TopN_PopulatedCities(){
        List<City> cities = app.report_TopN_PopulatedCities(0);
        assertNull(cities, "The result should be null");
        cities = app.report_TopN_PopulatedCities(-2);
        assertNull(cities, "The result should be null");
    }


    /**
     * Tests the report_TopN_PopulatedCitiesByContinent method to ensure it returns the top N populated cities in a continent.
     * sorted in descending order by population.
     */
    @Test
    void test_TopN_PopulatedCitiesByContinent(){
        List<City> cities = app.report_TopN_PopulatedCitiesByContinent("North America", 5);

        if (cities == null || cities.isEmpty()) return;

        // Exact value of items
        assertTrue(cities.size() <= 5, "There should be 5 or less cities returned");

        // Verify cities are in descending order
        if(cities.size() >= 2) {
            for (int i = 0; i < cities.size() - 1; i++) {
                assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                        "Cities should be ordered in descending population");
            }
        }else{
            assertEquals(1, cities.size());
        }
    }

    /**
     * Test method TopN_PopulatedCitiesByContinent to check if it can handle two null parameters
     */
    @Test
    void test_nullEmpty_TopN_PopulatedCitiesByContinent(){
        List<City> cities = app.report_TopN_PopulatedCitiesByContinent("", 0);
        assertNull(cities, "The result should be null");
        cities = app.report_TopN_PopulatedCitiesByContinent(null, 0);
        assertNull(cities, "The result should be null");
    }

    /**
     * Test method for TopN_PopulatedCitiesByContinent to check if it can handle 2 invalid inputs in its parameter
     */
    @Test
    void test_invalid_TopN_PopulatedCitiesByContinent(){
        List<City> cities = app.report_TopN_PopulatedCitiesByContinent("123",-2);
        assertNull(cities, "The result should be null");
    }

    /**
     * Tests the report_TopN_PopulatedCitiesByRegion method to ensure it returns the top N populated cities in a region.
     * sorted in descending order by population.
     */
    @Test
    void test_TopN_PopulatedCitiesByRegion(){
        List<City> cities = app.report_TopN_PopulatedCitiesByRegion("Central America", 4);

        // Not Null
        assertNotNull(cities, "The result should not be null");

        // Exact value of items
        assertEquals(4, cities.size(), "There should be 5 cities returned");

        // Verify cities are in descending order
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Countries should be ordered in descending population for the specified region");
        }
    }

    /**
     * Test method TopN_PopulatedCitiesByRegion to check if it can handle two null parameters
     */
    @Test
    void test_nullEmpty_TopN_PopulatedCitiesByRegion(){
        List<City> cities = app.report_TopN_PopulatedCitiesByRegion("", 0);
        assertNull(cities, "The result should be null");
        cities = app.report_TopN_PopulatedCitiesByRegion(null, 0);
        assertNull(cities, "The result should be null");
    }

    /**
     * Test method for TopN_PopulatedCitiesByRegion to check if it can handle 2 invalid inputs in its parameter
     */
    @Test
    void test_invalid_TopN_PopulatedCitiesByRegion(){
        List<City> cities = app.report_TopN_PopulatedCitiesByRegion("SouthWest Europe",-2);
        assertNull(cities, "The result should be null");
    }

    /**
     * Tests the report_TopN_PopulatedCitiesByCountry method to ensure it returns the top N populated cities in a Country.
     * sorted in descending order by population.
     */
    @Test
    void test_TopN_PopulatedCitiesByCountry(){
        List<City> cities = app.report_TopN_PopulatedCitiesByCountry("Brazil",2);

        // Not Null
        assertNotNull(cities, "The result should not be null");

        // Exact value of items
        assertEquals(2, cities.size(), "There should be 2 cities returned");

        // Verify cities are in descending order
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population for the specified country");
        }
    }

    /**
     * Test method TopN_PopulatedCitiesByCountry to check if it can handle two null parameters
     */
    @Test
    void test_nullEmpty_TopN_PopulatedCitiesByCountry(){
        List<City> cities = app.report_TopN_PopulatedCitiesByCountry("", 0);
        assertNull(cities, "The result should be null");
        cities = app.report_TopN_PopulatedCitiesByCountry(null, 0);
        assertNull(cities, "The result should be null");
    }

    /**
     * Test method for TopN_PopulatedCitiesByCountry to check if it can handle 2 invalid inputs in its parameter
     */
    @Test
    void test_invalid_TopN_PopulatedCitiesByCountry(){
        List<City> cities = app.report_TopN_PopulatedCitiesByCountry("Scotland",-2);
        assertNull(cities, "The result should be null");
    }

    /**
     * Tests the report_TopN_PopulatedCitiesByDistrict method to ensure it returns the top N populated cities in a District.
     * sorted in descending order by population.
     */
    @Test
    void test_TopN_PopulatedCitiesByDistrict(){
        List<City> cities = app.report_TopN_PopulatedCitiesByDistrict("Western",6);

        // Not Null
        assertNotNull(cities, "The result should not be null");

        // Exact value of items
        assertEquals(6, cities.size(), "There should be 6 cities returned");

        // Verify cities are in descending order
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population for the specified district");
        }
    }

    /**
     * Test method TopN_PopulatedCitiesByDistrict to check if it can handle two null parameters
     */
    @Test
    void test_nullEmpty_TopN_PopulatedCitiesByDistrict(){
        List<City> cities = app.report_TopN_PopulatedCitiesByDistrict("", 0);
        assertNull(cities, "The result should be null");
        cities = app.report_TopN_PopulatedCitiesByDistrict(null, 0);
        assertNull(cities, "The result should be null");
    }

    /**
     * Test method for TopN_PopulatedCitiesByDistrict to check if it can handle 2 invalid inputs in its parameter
     */
    @Test
    void test_invalid_TopN_PopulatedCitiesByDistrict(){
        List<City> cities = app.report_TopN_PopulatedCitiesByDistrict("Centre",-2);
        assertNull(cities, "The result should be null");
    }





    /**
     * Tests the report_PopulationDESC method to ensure it returns a list of all countries
     * sorted in descending order by population.
     */
    @Test
    void test_PopulationDESC(){
        // Get all country by population
        List<Country> countries = app.report_PopulationDESC();

        // Not Null
        assertNotNull(countries, "The result should not be null");

        // Has items
        assertNotEquals(0, countries.size(), "Countries should not be empty");

        // Verify items are in descending order
        for (int i = 0; i < countries.size() - 1; i++) {
            assertTrue(countries.get(i).Population >= countries.get(i + 1).Population,
                    "Countries should be ordered in descending order.");
        }
    }

    /**
     * Tests the report_PopulationByContinentDESC method to ensure it return a list of countries
     * within a given continent.
     */
    @Test
    void test_PopulationByContinentDESC(){
        // Get population
        List<Country> countries = app.report_PopulationByContinentDESC("Asia");

        // Not Null
        assertNotNull(countries, "The result should not be null");

        // Verify countries are in descending order
        for (int i = 0; i < countries.size() - 1; i++) {
            assertTrue(countries.get(i).getPopulation() >= countries.get(i + 1).getPopulation(),
                    "Countries should be ordered in descending population for the specified continent");
        }
    }

    /**
     * Test method for PopulationByContinentDESC to check if it can handle empty parameter
     */
    @Test
    void test_nullEmpty_PopulationByContinentDESC(){
        List<Country> countries = app.report_PopulationByContinentDESC("");
        assertNull(countries, "The result should be null");
        countries = app.report_PopulationByContinentDESC(null);
        assertNull(countries, "The result should be null");
    }

    /**
     * Test method for PopulationByContinentDESC to check if it can handle invalid input in its parameter
     */
    @Test
    void test_invalid_PopulationByContinentDESC(){
        List<Country> countries = app.report_PopulationByContinentDESC("Scotland");
        assertNotNull(countries, "The result should be not null");
        assertEquals(0, countries.size(), "The result should be empty.");
    }

    /**
     * Tests the report_CountriesByRegion method to ensure it returns a list of countries
     * within a given region.
     */
    @Test
    void test_CountriesByRegionDESC(){
        // Get countries by region
        List<Country> countries = app.report_CountriesByRegionDESC("South America");

        // Not null
        assertNotNull(countries, "The result should not be null");

        // Verify countries returned in descending order by population
        for (int i = 0; i < countries.size() - 1; i++) {
            assertTrue(countries.get(i).getPopulation() >= countries.get(i + 1).getPopulation(),
                    "Countries should be ordered in descending population for the specified region");
        }
    }


    /**
     * Test method CountriesByRegionDESC to check if it can handle a null parameter
     */
    @Test
    void test_nullEmpty_CountriesByRegionDESC(){
        List<Country> countries = app.report_CountriesByRegionDESC(null);
        assertNull(countries, "The result should be null");
        countries = app.report_CountriesByRegionDESC("");
        assertNull(countries, "The result should be null");
    }

    /**
     * Test method for CountriesByRegionDESC to check if it can handle invalid inpput
     */
    @Test
    void test_invalid_CountriesByRegionDESC(){
        List<Country> countries = app.report_CountriesByRegionDESC("Africa");
        assertNotNull(countries, "The result should not be null");
        assertEquals(0, countries.size(), "The result should be empty.");
    }

    /**
     * Tests the report_CapitalCitiesInRegionDESC method to ensure it returns
     * a list of capital cities within the given region
     */
    @Test
    void test_CapitalCitiesInRegionDESC() {
        List<City> items = app.report_CapitalCitiesInRegionDESC("Western Europe");
        // Not empty
        assertNotEquals(0, items.size());

        // Check order of items
        for (int i = 0; i < items.size() - 1; i++) {
            assertTrue(items.get(i).Population >= items.get(i + 1).Population,
                    "Cities should be ordered in descending population");
        }
    }

    /**
     * Test if CapitalCitiesInRegionDESC can handle null parameter
     */
    @Test
    void test_nullEmpty_CapitalCitiesInRegionDESC(){
        List<City> cities = app.report_CapitalCitiesInRegionDESC(null);
        assertNull(cities, "The result should be null");
        cities = app.report_CapitalCitiesInRegionDESC("");
        assertNull(cities, "The result should be null");
    }

    /**
     * Test if CapitalCitiesInRegionDESC can handle invalid parameter
     */
    @Test
    void test_invalid_CapitalCitiesInRegionDESC(){
        List<City> cities = app.report_CapitalCitiesInRegionDESC("Scotland");
        assertEquals(0, cities.size());
    }

    /**
     * Test report_CitiesInWorldDESC outputs correct number of reports & if it is pulling the correct data.
     *  And to also check if it's outputting in DESC order
     */
    @Test
    void test_report_CitiesInWorldDESC(){
        // Put the report into a list
        List<City> cities = app.report_CitiesInWorldDESC();

        // Check if the size of cities is the same as expected
        assertNotNull(cities, "The result should not be null");
        assertNotEquals(0, cities.size());

        // Check if in desc
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population");
        }
    }


    /**
     * Tests the report CitiesInContinentDESC method to ensure it returns a list of all cities
     * sorted in descending order by population within a given continent.
     */
    @Test
    void test_report_CitiesInContinentDESC(){
        // Get cities in continent
        List<City> cities = app.report_CitiesInContinentDESC("Africa");

        // Not nukll
        assertNotNull(cities, "The result should not be null");

        // Verify cities are in descending order
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population");
        }
    }

    /**
     * Tests report CitiesInContinentDESC for empty and null as parameters
     */
    @Test
    void test_emptyNull_CitiesInContinentDESC(){
        List<City> cities = app.report_CitiesInContinentDESC("");
        assertNull(cities);
        cities = app.report_CitiesInContinentDESC(null);
        assertNull(cities);
    }

    /**
     * Tests the report CitiesInRegionDESC method to ensure it returns a list of all cities
     * sorted in descending order by population within a given region.
     */
    @Test
    void test_report_CitiesInRegionDESC(){
        List<City> cities = app.report_CitiesInRegionDESC("Western Europe");
        // Not null
        assertNotNull(cities);

        // Verify cities are in descending order
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population");
        }
    }

    /**
     * Tests report CitiesInRegionDESC for empty and null as parameters
     */
    @Test
    void test_emptyNull_CitiesInRegionDESC(){
        List<City> cities = app.report_CitiesInRegionDESC("");
        assertNull(cities);
        cities = app.report_CitiesInRegionDESC(null);
        assertNull(cities);
    }

    /**
     * Tests the report CitiesInCountryDESC method to ensure it returns a list of all cities
     * sorted in descending order by population within a given country.
     */
    @Test
    void test_report_CitiesInCountryDESC(){
        List<City> cities = app.report_CitiesInCountryDESC("France");

        // Not null
        assertNotNull(cities);

        // Verify cities are in descending order
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population");
        }
    }

    /**
     * Tests report CitiesInCountryDESC for empty and null as parameters
     */
    @Test
    void test_emptyNull_CitiesInCountryDESC(){
        List<City> cities = app.report_CitiesInCountryDESC("");
        assertNull(cities);
        cities = app.report_CitiesInCountryDESC(null);
        assertNull(cities);
    }

    /**
     * Tests the report CitiesInDistrictDESC method to ensure it returns a list of all cities
     * sorted in descending order by population within a given district.
     */
    @Test
    void test_report_CitiesInDistrictDESC(){
        List<City> cities = app.report_CitiesInDistrictDESC("Scotland");

        // Not Null
        assertNotNull(cities);

        // Verify cities are in descending order
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population");
        }
    }

    /**
     * Tests report CitiesInDistrictDESC for empty and null as parameters
     */
    @Test
    void test_emptyNull_CitiesInDistrictDESC(){
        List<City> cities = app.report_CitiesInDistrictDESC("");
        assertNull(cities);
        cities = app.report_CitiesInDistrictDESC(null);
        assertNull(cities);
    }

    /**
     * Tests the report CapitalCitiesInWorldDESC method to ensure it returns a list of all cities
     * sorted in descending order by population in the world.
     */
    @Test
    void test_report_CapitalCitiesInWorldDESC(){
        List<City> cities = app.report_CapitalCitiesInWorldDESC();

        // Not null or empty
        assertNotNull(cities);
        assertNotEquals(0, cities.size());

        // Check if in desc
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population");
        }
    }


    /**
     * Tests the report TopN_PopulatedCapitalCitiesInWorld to ensure it returns a list of cities
     * sorted in decensding order by population
     */
    @Test
    void test_report_TopN_PopulatedCapitalCitiesInWorld(){
        List<City> cities = app.report_TopN_PopulatedCapitalCitiesInWorld(5);
        // Not null
        assertNotNull(cities);

        if(!cities.isEmpty()){
            // Within range
            assertTrue(cities.size() <= 5);

            // Check if in desc
            assertTrue(isDescending_City(cities));
        }
    }

    /**
     * Tests report TopN_PopulatedCapitalCitiesInWorld with invalid parameters
     */
    @Test
    void test_report_TopN_PopulatedCapitalCitiesInWorld_invalid(){
        assertNull(app.report_TopN_PopulatedCapitalCitiesInWorld(-1));
    }

    /**
     * Tests report TopN_PopulatedCapitalCitiesInContinent to ensure it returns a list of cities
     * sorted and structured in descensing order
     */
    @Test
    void test_report_TopN_PopulatedCapitalCitiesInContinent(){
        List<City> cities = app.report_TopN_PopulatedCapitalCitiesInContinent(3, "Asia");
        // Not null
        assertNotNull(cities);

        if(!cities.isEmpty()){
            // Within range
            assertTrue(cities.size() <= 3);
            // Check if in desc
            assertTrue(isDescending_City(cities));
        }
    }

    /**
     * Tests report TopN_PopulatedCapitalCitiesInContinent for non-existing continents and invalid parameters
     */
    @Test
    void test_report_TopN_PopulatedCapitalCitiesInContinent_invalid(){
        // Negative limit
        List<City> cities = app.report_TopN_PopulatedCapitalCitiesInContinent(-1, "Asia");
        assertNull(cities, "Cities should be null on negative limit");
        // Non-existing continent
        cities = app.report_TopN_PopulatedCapitalCitiesInContinent(3, "111");
        assertNotNull(cities, "Cities should be not null on non-existing continent");
        assertTrue(cities.isEmpty(), "Cities should be empty on non-existing continent");
        // null string
        cities = app.report_TopN_PopulatedCapitalCitiesInContinent(3, null);
        assertNull(cities, "Cities should be null on null continent");
    }

    /**
     * Tests report TopN_PopulatedCapitalCitiesInRegion to ensure it returns a list of cities within given region
     * and in a decsending order
     */
    @Test
    void test_report_TopN_PopulatedCapitalCitiesInRegion(){
        List<City> cities = app.report_TopN_PopulatedCapitalCitiesInRegion(3, "Central America");
        // Not null
        assertNotNull(cities);

        if(!cities.isEmpty()){
            // Within range
            assertTrue(cities.size() <= 3);
            // Check if in desc
            assertTrue(isDescending_City(cities));
        }
    }

    /**
     * Tests report TopN_PopulatedCapitalCitiesInRegion for non-existing regions and invalid parameters
     */
    @Test
    void test_report_TopN_PopulatedCapitalCitiesInRegion_invalid(){
        // Negative limit
        List<City> cities = app.report_TopN_PopulatedCapitalCitiesInRegion(-1, "Central America");
        assertNull(cities, "Cities should be null on negative limit");
        // Non-existing continent
        cities = app.report_TopN_PopulatedCapitalCitiesInRegion(3, "111");
        assertNotNull(cities, "Cities should be not null on non-existing continent");
        assertTrue(cities.isEmpty(), "Cities should be empty on non-existing continent");
        // null string
        cities = app.report_TopN_PopulatedCapitalCitiesInRegion(3, null);
        assertNull(cities, "Cities should be null on null continent");
    }

    /**
     * Tests report PopulationBreakdown_AllContinents for correctly structured population breakdown
     * grouped by continents
     */
    @Test
    void test_report_PopulationBreakdown_AllContinents(){
        List<PopulationBreakdown> lst = app.report_PopulationBreakdown_AllContinents();
        // Not null (can be empty)
        assertNotNull(lst);
        // validate integrity
        assertTrue(isDecendingAndValid_PopulationBreakdown(lst));
    }

    /**
     * Tests report PopulationBreakdown_AllRegions for correctly structured population breakdown,
     * grouped by regions
     */
    @Test
    void test_report_PopulationBreakdown_AllRegions(){
        List<PopulationBreakdown> lst = app.report_PopulationBreakdown_AllRegions();
        // Not null (can be empty)
        assertNotNull(lst);
        // validate integrity
        assertTrue(isDecendingAndValid_PopulationBreakdown(lst));
    }

    /**
     * Tests report PopulationBreakdown_AllCountries for correctly structured population breakdown,
     * grouped by countries
     */
    @Test
    void test_report_PopulationBreakdown_AllCountries(){
        List<PopulationBreakdown> lst = app.report_PopulationBreakdown_AllCountries();
        // Not null (can be empty)
        assertNotNull(lst);
        // validate integrity
        assertTrue(isDecendingAndValid_PopulationBreakdown(lst));
    }

    /**
     * Tests report TotalPopulation_World to ensure it returns a valid number
     */
    @Test
    void test_report_TotalPopulation_World(){
        long total = app.report_TotalPopulation_World();
        assertTrue(total >= 0);
    }

    /**
     * Tests report TotalPopulation_World for runtime exceptions in case of null connection
     */
    @Test
    void test_report_TotalPopulation_World_invalid(){
        App.con = null;
        assertThrows(RuntimeException.class, () ->
                app.report_TotalPopulation_World(), "Expected report_TotalPopulation_World to throw, but it didn't.");
    }

    /**
     * Tests report TotalPopulation_Continent to ensure it returns a valid number for total population within given continent
     */
    @Test
    void test_report_TotalPopulation_Continent(){
        long total = app.report_TotalPopulation_Continent("Asia");
        assertTrue(total >= 0);
    }

    /**
     * Tests report TotalPopulation_Continent for runtime exceptions in case of null connection
     */
    @Test
    void test_report_TotalPopulation_Continent_invalid(){
        App.con = null;
        assertThrows( RuntimeException.class, () ->
                app.report_TotalPopulation_Continent("Asia"), "Expected report_TotalPopulation_World to throw, but it didn't.");
    }

    /**
     * Tests report TotalPopulation_Continent for non-existing or null continent
     */
    @Test
    void test_report_TotalPopulation_Continent_emptyNull(){
        // Non-existing continent
        long total = app.report_TotalPopulation_Continent("123");
        assertEquals(0, total, "Expected 0 population for non-existing continent");
        // Null continent
        total = app.report_TotalPopulation_Continent(null);
        assertEquals(0, total, "Expected 0 population for null continent");
    }

    /**
     * Tests report TotalPopulation_Region to ensure it returns a valid number for total population within given region
     */
    @Test
    void test_report_TotalPopulation_Region(){
        long total = app.report_TotalPopulation_Region("Central America");
        assertTrue(total >= 0);
    }

    /**
     * Tests report TotalPopulation_Region for runtime exceptions in case of null connection
     */
    @Test
    void test_report_TotalPopulation_Region_invalid(){
        App.con = null;
        assertThrows( RuntimeException.class, () ->
                app.report_TotalPopulation_Region("Central America"), "Expected report_TotalPopulation_Region to throw, but it didn't.");
    }

    /**
     * Tests report TotalPopulation_Region for non-existing or null region
     */
    @Test
    void test_report_TotalPopulation_Region_emptyNull(){
        // Non-existing region
        long total = app.report_TotalPopulation_Region("123");
        assertEquals(0, total, "Expected 0 population for non-existing region");
        // Null region
        total = app.report_TotalPopulation_Region(null);
        assertEquals(0, total, "Expected 0 population for null region");
    }

    /**
     * Tests report TotalPopulation_Country to ensure it returns a valid number for total population within given country
     */
    @Test
    void test_report_TotalPopulation_Country(){
        long total = app.report_TotalPopulation_Country("Germany");
        // Not negative
        assertTrue(total >= 0, "Country population can not be negative");
    }

    /**
     * Tests report TotalPopulation_Country for runtime exceptions in case of null connection
     */
    @Test
    void test_report_TotalPopulation_Country_invalid(){
        App.con = null;
        assertThrows(RuntimeException.class, () ->
                app.report_TotalPopulation_Country("1"), "Expected report_TotalPopulation_Country to throw, but it didn't.");
    }

    /**
     * Tests report TotalPopulation_Country for non-existing or null country
     */
    @Test
    void test_report_TotalPopulation_Country_emptyNull(){
        // Non-existing country
        long total = app.report_TotalPopulation_Country("123");
        assertEquals(0, total, "Expected 0 population for non-existing country");
        // Null country
        total = app.report_TotalPopulation_Country(null);
        assertEquals(0, total, "Expected 0 population for null country");
    }

    /**
     * Tests report TotalPopulation_District to ensure it returns a valid number for total population within given district
     */
    @Test
    void test_report_TotalPopulation_District(){
        long total = app.report_TotalPopulation_District("Western");
        // Non negative population
        assertTrue(total >= 0, "District population can not be negative");
    }

    /**
     * Tests report TotalPopulation_District for runtime exceptions in case of null connection
     */
    @Test
    void test_report_TotalPopulation_District_invalid(){
        App.con = null;
        assertThrows(RuntimeException.class, () ->
                app.report_TotalPopulation_District("1"), "Expected report_TotalPopulation_District to throw, but it didn't.");
    }


    /**
     * Tests report TotalPopulation_District for non-existing or null district
     */
    @Test
    void test_report_TotalPopulation_District_emptyNull(){
        long total = app.report_TotalPopulation_District("");
        assertEquals(0, total, "Expected 0 population for non-existing district");
        total = app.report_TotalPopulation_District(null);
        assertEquals(0, total, "Expected 0 population for null district");
    }

    /**
     * Tests report TotalPopulation_City for a non-negative population with given city
     */
    @Test
    void test_report_TotalPopulation_City(){
        long total = app.report_TotalPopulation_City("London");
        assertTrue(total >= 0, "City population can not be negative");
    }

    /**
     * Tests report TotalPopulation_City for runtime exception in case of a null connection
     */
    @Test
    void test_report_TotalPopulation_City_invalid(){
        App.con = null;
        assertThrows(RuntimeException.class, () ->
                app.report_TotalPopulation_City("1"), "Expected report_TotalPopulation_City to throw, but it didn't.");
    }

    /**
     * Tests report TotalPopulation_City for non-existing and null city parameter
     */
    @Test
    void test_report_TotalPopulation_City_emptyNull(){
        long total = app.report_TotalPopulation_City("123");
        assertEquals(0, total, "Expected 0 population for non-existing city");
        total = app.report_TotalPopulation_City(null);
        assertEquals(0, total, "Expected 0 population for null city");
    }

    /**
     * Tests report WorldLanguagesBreakdown for integrity and structure of retrieved data, ensuring items are loaded and ordered in descending order.
     */
    @Test
    void test_report_WorldLanguagesBreakdown(){
        List<Language> languages = app.report_WorldLanguagesBreakdown(6000000000L);
        assertNotNull(languages);

        if(!languages.isEmpty()){
            // Validate populated fields
            for(Language language : languages){
                assertTrue(language.getLanguage() != null && !language.getLanguage().isEmpty(), "Language name can not be empty or null.");
                assertTrue(language.getSpeakers() >= 0, "Speakers can not be negative");
                assertTrue(language.getPercentage() >= 0, "Percentage can not be negative");
            }

            // Check languages are ordered by descending order of speakers
            if(languages.size() >= 2){
                for(int i = 0; i < languages.size()-1; i++){
                    assertTrue(languages.get(i).getSpeakers() >= languages.get(i+1).getSpeakers(), "Languages have to be ordered by descending order of total pseakers.");
                }
            }
        }
    }

    /**
     * Tests report WorldLanguagesBreakdown for empty and invalid world population
     */
    @Test
    void test_report_WorldLanguagesBreakdown_emptyNull(){
        List<Language> languages = app.report_WorldLanguagesBreakdown(0);
        assertNotNull(languages);

        // Languages with 0 world population will result in zero percentage
        if(!languages.isEmpty()){
            for(Language language : languages){
                assertEquals(0, language.getPercentage(), "Percentage should be zero with zero world population");
            }
        }

        languages = app.report_WorldLanguagesBreakdown(-1);
        assertNull(languages, "Languages list should be null with negative world population");

    }

    /**
     * Test generic PrintItems with Null
     */
    @Test
    void test_emptyNull_printItems(){
        App.print_Items(null);
        App.print_Items(Collections.emptyList());
    }

    /**
     * Test generic PrintItems with single and multiple items
     */
    @Test
    void test_singleAndMany_printItems(){
        List<String> items = List.of("item0");
        App.print_Items(items);
        items = List.of("item1", "item2", "item3");
        App.print_Items(items);
        System.out.println();
    }

    /**
     * Tests all reports by printing all of them at once.
     */
    @Test
    void test_allReports(){
        try{
            App.generateAllReports(app);
        }catch (Exception e){
            throw new AssertionError(e);
        }
    }

    /**
     * Tests invalid query execution order
     */
    @Test
    void test_executeQuery_invalid(){
        assertThrows( RuntimeException.class, () ->
                App.executeQuery("1"), "Invalid query should throw");
    }

    /**
     * Test Language object constructor for its Percentage validation
     */
    @Test
    void test_class_language(){
        // Check for NaN and Infinity values in percentage
        Language lang = new Language("test", 1, Double.NaN);
        assertEquals(0, lang.getPercentage());
        lang = new Language("test", 1, Double.POSITIVE_INFINITY);
        assertEquals(0, lang.getPercentage());

        // Check for above 100 or below 0 percentages
        lang = new Language("test", 1, -1);
        assertEquals(0, lang.getPercentage());
        lang = new Language("test", 1, 101);
        assertEquals(100, lang.getPercentage());
    }

    /**
     * Tests class City and its getters
     */
    @Test
    void test_class_city(){
        // Create a new City object and verify getters returning correct values
        City c = new City(1, "CityName", "ABC", "A", 2);
        assertEquals(1, c.getID());
        assertEquals("CityName", c.getName());
        assertEquals("ABC", c.getCountryCode());
        assertEquals("A", c.getDistrict());
        assertEquals(2, c.getPopulation());
    }

    @Test
    void test_class_country(){
        // Create a new Country object and verify getters returning correct values
        Country c = new Country("Code", "Name", "Continent", "Region", 2,
                3, 4, 5.0,6.0 , 7.0, "LocalName",
                "GovernmentForm", "HeadOfState", 8, "Code2");
        assertEquals("Code", c.getCode());
        assertEquals("Name", c.getName());
        assertEquals("Continent", c.getContinent());
        assertEquals("Region", c.getRegion());
        assertEquals(2, c.getSurfaceArea());
        assertEquals(3, c.getIndepYear());
        assertEquals(4, c.getPopulation());
        assertEquals(5.0d, c.getLifeExpectancy());
        assertEquals(6.0d, c.getGNP());
        assertEquals(7.0d, c.getGNPOld());
        assertEquals("LocalName", c.getLocalName());
        assertEquals("GovernmentForm", c.getGovernmentForm());
        assertEquals("HeadOfState", c.getHeadOfState());
        assertEquals(8, c.getCapital());
        assertEquals("Code2", c.getCode2());


    }

    // UTIL Methods //
    public boolean isDescending_City(List<City> cities){
        if (cities == null || cities.isEmpty()) return false;
        // Check if in desc
        if(cities.size() > 1){
            for (int i = 0; i < cities.size() - 1; i++) {
               if (cities.get(i).Population < cities.get(i + 1).Population)
                   return false;
            }
        }

        return true;
    }

    public boolean isDecendingAndValid_PopulationBreakdown(List<PopulationBreakdown> lst){
        if(!lst.isEmpty()){
            // Populated class fields
            for (PopulationBreakdown pop : lst) {
                // Strings are not empty/null
                if(pop.getName() == null || pop.getName().isEmpty())
                    return false;
                if(pop.getTitle() == null || pop.getTitle().isEmpty())
                    return false;
                // Pop not negative
                if(pop.getTotalPopulation() < 0)
                    return false;
                if(pop.getRuralPopulation() < 0)
                    return false;
                if(pop.getUrbanPopulation() < 0)
                    return false;
            }
            // In desc order
            if(lst.size() >= 2){
                for(int i = 0 ; i < lst.size() -1; i++){
                    if(lst.get(i).getTotalPopulation() < lst.get(i+1).getTotalPopulation())
                        return false;
                }
            }
        }
        return  true;
    }
}

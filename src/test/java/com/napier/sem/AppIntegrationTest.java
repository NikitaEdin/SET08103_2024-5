package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;

    /**
     * Method to initialise required logic before the tests are executed
     */
    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
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

        // Not Null
        assertNotNull(cities, "The result should not be null");

        // Exact value of items
        assertEquals(5, cities.size(), "There should be 5 cities returned");

        // Verify cities are in descending order
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population");
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
        List<City> cities = app.report_TopN_PopulatedCitiesByContinent("Eastern Europe",-2);
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

    @Test
    void test_allReports(){
        try{
            App.generateAllReports(app);
        }catch (Exception e){
            throw new AssertionError(e);
        }
    }

}
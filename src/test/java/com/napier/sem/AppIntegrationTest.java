package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
     * Method that connects to a database
     */
    @Test
    void testConnect(){
        app.connect("localhost:33060", 30000);
    }

    /**
     * Method that tests if the location to the sql database is empty
     */
    @Test
    void testConnectEmpty(){
        app.connect("",0);
    }

    /**
     * Method that tests if the location to the sql database is null
     */
    @Test
    void testConnectNull(){
        app.connect(null,0);
    }

    /**
     * Method that disconnects from the database
     */
    @Test
    void testDisconnect() {
        app.disconnect();
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

        // Exact value of items
        assertEquals(239, countries.size(), "There should be 239 countries returned");

        // Verify descending order by population
        assertTrue(countries.get(0).Population >= countries.get(1).Population,
                "First country population should be greater than or equal to the second");
        assertTrue(countries.get(1).Population >= countries.get(2).Population,
                "Second country population should be greater than or equal to the third");

        // Verify specific order
        assertEquals("China", countries.get(0).getName(), "The first country should be China.");
        assertEquals("India", countries.get(1).getName(), "The second country should be India.");
        assertEquals("United States", countries.get(2).getName(), "The third country should be United States.");
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

        // Verify top 3 countries
        assertEquals(1277558000, countries.get(0).getPopulation());
        assertEquals(1013662000, countries.get(1).getPopulation());
        assertEquals(212107000, countries.get(2).getPopulation());

        // Verify top 3 countries by name
        assertEquals("China", countries.get(0).getName(), "The first country should be China.");
        assertEquals("India", countries.get(1).getName(), "The second country should be India.");
        assertEquals("Indonesia", countries.get(2).getName(), "The third country should be Indonesia.");
    }

    /**
     * Test method for PopulationByContinentDESC to check if it can handle empty parameter
     */
    @Test
    void test_empty_PopulationByContinentDESC(){
        List<Country> countries = app.report_PopulationByContinentDESC("");
    }

    /**
     * Test method for PopulationByContinentDESC to check if it can handle null parameter
     */
    @Test
    void test_null_PopulationByContinentDESC(){
        List<Country> countries = app.report_PopulationByContinentDESC(null);
    }

    /**
     * Test method for PopulationByContinentDESC to check if it can handle invalid input in it's parameter
     */
    @Test
    void test_invalid_PopulationByContinentDESC(){
        List<Country> countries = app.report_PopulationByContinentDESC("Scotland");
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

        // Verify top 3 countries
        assertEquals(170115000, countries.get(0).getPopulation());
        assertEquals(42321000, countries.get(1).getPopulation());
        assertEquals(37032000, countries.get(2).getPopulation());

        // Verify top 3 countries by name
        assertEquals("Brazil", countries.get(0).getName(), "The first country should be Brazil.");
        assertEquals("Colombia", countries.get(1).getName(), "The second country should be Colombia.");
        assertEquals("Argentina", countries.get(2).getName(), "The third country should be Argentina.");


    }

    /**
     * Test method CountriesByRegionDESC to check if it can handle a empty parameter
     */
    @Test
    void test_empty_CountriesByRegionDESC(){
        List<Country> countries = app.report_CountriesByRegionDESC("");
    }

    /**
     * Test method CountriesByRegionDESC to check if it can handle a null parameter
     */
    @Test
    void test_null_CountriesByRegionDESC(){
        List<Country> countries = app.report_CountriesByRegionDESC(null);
    }

    /**
     * Test method for CountriesByRegionDESC to check if it can handle invalid inpput
     */
    @Test
    void test_invalid_CountriesByRegionDESC(){
        List<Country> countries = app.report_CountriesByRegionDESC("Africa");
    }

    /**
     * Tests the report_CapitalCitiesInRegionDESC method to ensure it returns
     * a list of capital cities within the given region
     */
    @Test
    void test_CapitalCitiesInRegionDESC() {
        List<City> items = app.report_CapitalCitiesInRegionDESC("Western Europe");
        // Chec for specific item count
        assertEquals(items.size(), 9);

        // Check order of items
        for (int i = 0; i < items.size() - 1; i++) {
            assertTrue(items.get(i).Population >= items.get(i + 1).Population,
                    "Cities should be ordered in descending population");
        }

        // Check exact city names in exact order
        assertEquals("Berlin", items.get(0).getName(), "The first country should be Berlin.");
        assertEquals("Paris", items.get(1).getName(), "The second country should be Paris.");
        assertEquals("Wien", items.get(2).getName(), "The third country should be Wien.");
    }

    /**
     * Test if CapitalCitiesInRegionDESC can handle empty parameter
     */
    @Test
    void test_empty_CapitalCitiesInRegionDESC(){
        List<City> cities = app.report_CapitalCitiesInRegionDESC("");
    }

    /**
     * Test if CapitalCitiesInRegionDESC can handle null parameter
     */
    @Test
    void test_null_CapitalCitiesInRegionDESC(){
        List<City> cities = app.report_CapitalCitiesInRegionDESC(null);
    }

    /**
     * Test if CapitalCitiesInRegionDESC can handle invalid parameter
     */
    @Test
    void test_invalid_CapitalCitiesInRegionDESC(){
        List<City> cities = app.report_CapitalCitiesInRegionDESC("Scotland");
    }

    /**
     * Test report_CitiesInWorldDESC outputs correct number of reports & if it is pulling the correct data.
     *  And to also check if its outputting in DESC order
     */
    @Test
    void test_report_CitiesInWorldDESC(){
        // Put the report into a list
        List<City> cities = app.report_CitiesInWorldDESC();

        // Check if the size of cities is the same as expected
        assertEquals(4079, cities.size());

        // Check that the cities pulled are correct
        assertEquals("Mumbai (Bombay)", cities.get(0).getName(),"The first city should be Mumbai (Bombay).");
        assertEquals("Seoul", cities.get(1).getName(),"The second city should be Seoul.");
        assertEquals("São Paulo", cities.get(2).getName(),"The third city should be São Paulo .");

        // Check if in desc
        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(cities.get(i).Population >= cities.get(i + 1).Population,
                    "Cities should be ordered in descending population");
        }
    }



}
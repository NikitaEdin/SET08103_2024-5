package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.napier.sem.City;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class testing the City class methods.
 */
class CityTest {
    // Initialising City object and variables that will be used to create City object.
    static private City city;
    static private City cityNull;
    static private int id = 64;
    static private String name = "Dubai";
    static private String countryCode = "ARE";
    static private String district = "Dubai";
    static private int population = 669181;

    /**
     * Sets up the test fixture before all test methods run.
     */
    @BeforeAll
    static void setUpBeforeClass(){
        city = new City(id, name, countryCode, district, population);
        cityNull = new City(0, null, null, null, 0);
    }

    /**
     * Tests the City class constructor.
     */
    @Test
    void testCityConstructor() {
        assertEquals(id, city.ID);
        assertEquals(name, city.Name);
        assertEquals(countryCode, city.CountryCode);
        assertEquals(district, city.District);
        assertEquals(population, city.Population);
    }

    /**
     * Tests the City class constructor with null values.
     */
    @Test
    void testCityConstructorNull() {
        assertEquals(0, cityNull.ID);
        assertNull(cityNull.Name);
        assertNull(cityNull.CountryCode);
        assertNull(cityNull.District);
        assertEquals(0, cityNull.Population);
    }

    /**
     * Tests the getID method.
     */
    @Test
    void testGetID() {
        assertEquals(id, city.getID());
    }

    /**
     * Tests the getName method.
     */
    @Test
    void testGetName() {
        assertEquals(name, city.getName());
    }

    /**
     * Tests the getName method with a null value.
     */
    @Test
    void testGetNameNull(){
        assertNull(cityNull.getName());
    }

    /**
     * Tests the getCountryCode method.
     */
    @Test
    void testGetCountryCode() {
        assertEquals(countryCode, city.getCountryCode());
    }

    /**
     * Tests the getCountryCode method with a null value.
     */
    @Test
    void testGetCountryCodeNull(){
        assertNull(cityNull.getCountryCode());
    }

    /**
     * Tests the getDistrict method.
     */
    @Test
    void testGetDistrict() {
        assertEquals(district, city.getDistrict());
    }

    /**
     * Tests the getDistrict method with a null value.
     */
    @Test
    void testGetDistrictNull(){
        assertNull(cityNull.getDistrict());
    }

    /**
     * Tests the getPopulation method.
     */
    @Test
    void testGetPopulation() {
        assertEquals(population, city.getPopulation());
    }
}
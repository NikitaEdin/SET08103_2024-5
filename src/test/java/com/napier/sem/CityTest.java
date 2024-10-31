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
}
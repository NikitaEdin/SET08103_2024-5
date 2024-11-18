package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.napier.sem.CountryLanguage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class testing the CountryLanguage class methods.
 */
class CountryLanguageTest {
    // Initialising CountryLanguage object and variables that will be used to create CountryLanguage object.
    static private CountryLanguage cl;
    static private String countryCode = "ALB";
    static private String language = "Macedonian";
    static private String isOfficial = "F";
    static private double percentage = 0.1;

    /**
     * Sets up the test fixture before all test methods run.
     */
    @BeforeAll
    static void setUpBeforeClass(){
        cl = new CountryLanguage(countryCode, language, isOfficial, percentage);
    }

    /**
     * Tests the CountryLanguage constructor.
     */
    @Test
    void testCountryLanguageConstructor() {
        assertEquals(countryCode, cl.CountryCode);
        assertEquals(language, cl.Language);
        assertEquals(isOfficial, cl.IsOfficial);
        assertEquals(percentage, cl.Percentage);
    }

    /**
     * Tests the getCountryCode method.
     */
    @Test
    void testGetCountryCode() {
        assertEquals(countryCode, cl.getCountryCode());
    }

    /**
     * Tests the getLanguage method.
     */
    @Test
    void testGetLanguage() {
        assertEquals(language, cl.getLanguage());
    }

    /**
     * Tests the getIsOfficial method.
     */
    @Test
    void testGetIsOfficial() {
        assertEquals(isOfficial, cl.getIsOfficial());
    }

    /**
     * Tests the getPercentage method.
     */
    @Test
    void testGetPercentage() {
        assertEquals(percentage, cl.getPercentage());
    }
}
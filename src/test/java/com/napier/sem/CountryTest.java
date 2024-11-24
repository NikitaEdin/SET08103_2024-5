package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.napier.sem.Country;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class testing the Country class methods.
 */
class CountryTest {
    // Initialising Country object and variables that will be used to create Country object.
    static private Country country;
    static private Country countryNull;
    static private final String code = "AND";
    static private final String name = "Andorra";
    static private final String continent = "Europe";
    static private final String region = "Southern Europe";
    static private final double surfaceArea = 468.00;
    static private final int indepYear = 1278;
    static private final int population = 78000;
    static private final double lifeExpectancy = 83.5;
    static private final double gnp = 1630.00;
    static private final double gnpOld = 0.0;
    static private final String localName = "Andorra";
    static private final String governmentForm = "Parliamentary Coprincipality";
    static private final String headOfState = "";
    static private final int capital = 55;
    static private final String code2 = "AD";

    /**
     * Sets up the test fixture before all test methods run.
     */
    @BeforeAll
    static void setUpBeforeClass(){
        country = new Country(
                code,
                name,
                continent,
                region,
                surfaceArea,
                indepYear,
                population,
                lifeExpectancy,
                gnp,
                gnpOld,
                localName,
                governmentForm,
                headOfState,
                capital,
                code2 );

        countryNull = new Country(
                null,
                null,
                null,
                null,
                0,
                0,
                0,
                0,
                0,
                0,
                null,
                null,
                null,
                0,
                null);
    }

    /**
     * Tests the Country class constructor.
     */
    @Test
    void testCountryConstructor(){
        assertEquals(code, country.Code);
        assertEquals(name, country.Name);
        assertEquals(continent, country.Continent);
        assertEquals(region, country.Region);
        assertEquals(surfaceArea, country.SurfaceArea, 0.2);
        assertEquals(indepYear, country.IndepYear, 4);
        assertEquals(population, country.Population);
        assertEquals(lifeExpectancy, country.LifeExpectancy, 0.1);
        assertEquals(gnp, country.GNP, 0.2);
        assertEquals(gnpOld, country.GNPOld, 0.2);
        assertEquals(localName, country.LocalName);
        assertEquals(governmentForm, country.GovernmentForm);
        assertEquals(headOfState, country.HeadOfState);
        assertEquals(capital, country.Capital);
        assertEquals(code2, country.Code2);
    }

    /**
     * Tests the Country class constructor with null values.
     */
    @Test
    void testCountryConstructorNull(){
        assertNull(countryNull.Code);
        assertNull(countryNull.Name);
        assertNull(countryNull.Continent);
        assertNull(countryNull.Region);
        assertEquals(0, countryNull.LifeExpectancy);
        assertEquals(0, countryNull.GNP);
        assertEquals(0, countryNull.GNPOld);
        assertNull(countryNull.LocalName);
        assertNull(countryNull.GovernmentForm);
        assertNull(countryNull.HeadOfState);
        assertEquals(0, countryNull.Capital);
        assertNull(countryNull.Code2);
    }

    /**
     * Tests the getCode method.
     */
    @Test
    void testGetCode(){
        assertEquals(code, country.getCode());
    }

    /**
     * Tests the getCode method with a null value.
     */
    @Test
    void testGetCodeNull(){
        assertNull(countryNull.getCode());
    }

    /**
     * Tests the getName method.
     */
    @Test
    void testGetName(){
        assertEquals(name, country.getName());
    }

    /**
     * Tests the getName method with a null value.
     */
    @Test
    void testGetNameNull(){
        assertNull(countryNull.getName());
    }

    /**
     * Tests the getContinent method.
     */
    @Test
    void testGetContinent(){
        assertEquals(continent, country.getContinent());
    }

    /**
     * Tests the getContinent method with a null value.
     */
    @Test
    void testGetContinentNull(){
        assertNull(countryNull.getContinent());
    }

    /**
     * Tests the getRegion method.
     */
    @Test
    void testGetRegion(){
        assertEquals(region, country.getRegion());
    }

    /**
     * Tests the getRegion method with a null value.
     */
    @Test
    void testGetRegionNull(){
        assertNull(countryNull.getRegion());
    }

    /**
     * Tests the getSurfaceArea method.
     */
    @Test
    void testGetSurfaceArea(){
        assertEquals(surfaceArea, country.getSurfaceArea(), 0.2);
    }

    /**
     * Tests the getIndepYear method.
     */
    @Test
    void testGetIndepYear(){
        assertEquals(indepYear, country.getIndepYear(), 4);
    }

    /**
     * Tests the getPopulation method.
     */
    @Test
    void testGetPopulation(){
        assertEquals(population, country.getPopulation());
    }

    /**
     * Tests the getLifeExpectancy method.
     */
    @Test
    void testGetLifeExpectancy(){
        assertEquals(lifeExpectancy, country.getLifeExpectancy(), 0.1);
    }

    /**
     * Tests the getGNP method.
     */
    @Test
    void testGetGNP(){
        assertEquals(gnp, country.getGNP(), 0.2);
    }

    /**
     * Tests the getGNPOld method.
     */
    @Test
    void testGetGNPOld(){
        assertEquals(gnpOld, country.getGNPOld(), 0.2);
    }

    /**
     * Tests the getLocalName method.
     */
    @Test
    void testGetLocalName(){
        assertEquals(localName, country.getLocalName());
    }

    /**
     * Tests the getLocalName method with a null value.
     */
    @Test
    void testGetLocalNameNull(){
        assertNull(countryNull.getLocalName());
    }

    /**
     * Tests the getGovernmentForm method.
     */
    @Test
    void testGetGovernmentForm(){
        assertEquals(governmentForm, country.getGovernmentForm());
    }

    /**
     * Tests the getGovernmentForm method with a null value.
     */
    @Test
    void testGetGovernmentFormNull(){
        assertNull(countryNull.getGovernmentForm());
    }

    /**
     * Tests the getHeadOfState method
     */
    @Test
    void testGetHeadOfState(){
        assertEquals(headOfState, country.getHeadOfState());
    }

    /**
     * Tests the getHeadOfState method with a null value.
     */
    @Test
    void testGetHeadOfStateNull(){
        assertNull(countryNull.getHeadOfState());
    }

    /**
     * Tests the getCapital method.
     */
    @Test
    void testGetCapital(){
        assertEquals(capital, country.getCapital());
    }

    /**
     * Tests the getCode2 method.
     */
    @Test
    void testGetCode2(){
        assertEquals(code2, country.getCode2());
    }

    /**
     * Tests the getCode2 method with a null value.
     */
    @Test
    void testGetCode2Null(){
        assertNull(countryNull.getCode2());
    }
}
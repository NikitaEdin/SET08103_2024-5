package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.napier.sem.Language;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class testing the Language class methods.
 */
class LanguageTest {
    // Initialising Language object and variables that will be used to create Language object.
    private static Language lng;
    private static Language lngNull;
    private static final String language = "English";
    private static final double speakers = 1000.56;
    private static final double percentage = 23.5;

    /**
     * Sets up the test fixture before all test methods run.
     */
    @BeforeAll
    static void setUpBeforeClass(){
        lng = new Language(language, speakers, percentage);
        lngNull = new Language(null, 0, 0);
    }

    /**
     * Tests the Language class constructor.
     */
    @Test
    void testLanguageConstructor(){
        assertEquals(language, lng.getLanguage());
        assertEquals(speakers, lng.getSpeakers());
        assertEquals(percentage, lng.getPercentage());
    }

    /**
     * Tests the Language class constructor with null values.
     */
    @Test
    void testLanguageConstructorNull(){
        assertNull(lngNull.getLanguage());
        assertEquals(0, lngNull.getSpeakers());
        assertEquals(0, lngNull.getPercentage());
    }

    /**
     * Tests the getLanguage method.
     */
    @Test
    void testGetLanguage(){
        assertEquals(language, lng.getLanguage());
    }

    /**
     * Tests the getLanguage method with a null value.
     */
    @Test
    void testGetLanguageNull(){
        assertNull(lngNull.getLanguage());
    }

    /**
     * Tests the getSpeakers method.
     */
    @Test
    void testGetSpeakers(){
        assertEquals(speakers, lng.getSpeakers());
    }

    /**
     * Tests the getPercentage method.
     */
    @Test
    void testGetPercentage(){
        assertEquals(percentage, lng.getPercentage());
    }

}
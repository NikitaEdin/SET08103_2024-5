package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.napier.sem.PopulationBreakdown;

import static org.junit.jupiter.api.Assertions.*;

class PopulationBreakdownTest {
    // Initialising PopulationBreakdown object and variables that will be used to create PopulationBreakdown object.
    private static PopulationBreakdown pb;
    private static PopulationBreakdown pbNull;
    private static PopulationBreakdown pbNegative;
    private static final String title = "Continent";
    private static final String name = "Africa";
    private static final long totalPopulation = 123456789;
    private static final long urbanPopulation = 120000000;
    private static final long ruralPopulation = 3456789;

    /**
     * Sets up the test fixture before all test methods run.
     */
    @BeforeAll
    static void setUpBeforeClass() {
        pb = new PopulationBreakdown(title,
                                     name,
                                     totalPopulation,
                                     urbanPopulation,
                                     ruralPopulation);

        pbNull = new PopulationBreakdown(null,
                                        null,
                                 0,
                                0,
                                 0);

        pbNegative = new PopulationBreakdown("Region",
                                            "Central America",
                                     -15,
                                    -10,
                                     -5);
    }

    /**
     * Tests the PopulationBreakdown constructor.
     */
    @Test
    void testPopulationBreakdownConstructor(){
        assertEquals(title,pb.getTitle());
        assertEquals(name,pb.getName());
        assertEquals(totalPopulation,pb.getTotalPopulation());
        assertEquals(urbanPopulation,pb.getUrbanPopulation());
        assertEquals(ruralPopulation,pb.getRuralPopulation());
    }

    /**
     * Tests the PopulationBreakdown constructor with null values.
     */
    @Test
    void testPopulationBreakdownConstructorNull(){
        assertNull(pbNull.getTitle());
        assertNull(pbNull.getName());
        assertEquals(0,pbNull.getTotalPopulation());
        assertEquals(0,pbNull.getUrbanPopulation());
        assertEquals(0,pbNull.getRuralPopulation());
    }

    /**
     * Tests the getTitle method.
     */
    @Test
    void testGetTitle(){
        assertEquals(title,pb.getTitle());
    }

    /**
     * Tests the getTitle method with a null value.
     */
    @Test
    void testGetTitleNull(){
        assertNull(pbNull.getTitle());
    }

    /**
     * Tests the getName method.
     */
    @Test
    void testGetName(){
        assertEquals(name,pb.getName());
    }

    /**
     * Tests the getName method with a null value.
     */
    @Test
    void testGetNameNull(){
        assertNull(pbNull.getName());
    }

    /**
     * Tests the getTotalPopulation method.
     */
    @Test
    void testGetTotalPopulation(){
        assertEquals(totalPopulation,pb.getTotalPopulation());
    }

    /**
     * Tests the getTotalPopulation method with a negative value.
     */
    @Test
    void testGetTotalPopulationNegative(){
        assertEquals(0, pbNegative.getTotalPopulation());
    }

    /**
     * Tests the getUrbanPopulation method.
     */
    @Test
    void testGetUrbanPopulation(){
        assertEquals(urbanPopulation,pb.getUrbanPopulation());
    }

    /**
     * Tests the getUrbanPopulation method with a negative value.
     */
    @Test
    void testGetUrbanPopulationNegative(){
        assertEquals(0, pbNegative.getUrbanPopulation());
    }

    /**
     * Tests the getRuralPopulation method.
     */
    @Test
    void testGetRuralPopulation(){
        assertEquals(ruralPopulation,pb.getRuralPopulation());
    }

    /**
     * Tests the getRuralPopulation method with a negative value.
     */
    @Test
    void testGetRuralPopulationNegative(){
        assertEquals(0, pbNegative.getRuralPopulation());
    }
}
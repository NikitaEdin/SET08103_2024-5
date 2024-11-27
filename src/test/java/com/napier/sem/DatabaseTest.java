package com.napier.sem;

import static com.napier.sem.AppTest.app;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * This class is to the database using Mockito framework to test functionality of methods that require a database connection.
 */
public class DatabaseTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    private App app;

    /**
     * This is a setup class which sets up the mock connection of the database with Mockito
     * @throws SQLException
     */
    @BeforeEach
    void setUp() throws SQLException {
        App app = new App();
        MockitoAnnotations.openMocks(this);

        // Set the connection
        App.setConnection(mockConnection);

        // Mock creating a Statement and executing a query
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
    }

    /**
     * Tests  executeQuery method using Mockito to test that it can execute a query.
     * @throws SQLException
     */
//    @Test
//    void testExecuteQuery() throws SQLException {
//        // Define the query to test
//        String testQuery = "SELECT * FROM country";
//
//        // Call the method
//        ResultSet result = App.executeQuery(testQuery);
//
//        // Compare
//        assertNotNull(result);
//        assertEquals(mockResultSet, result);
//
//        // Verify
//        verify(mockConnection).createStatement();
//        verify(mockStatement).executeQuery(testQuery);
//    }

}


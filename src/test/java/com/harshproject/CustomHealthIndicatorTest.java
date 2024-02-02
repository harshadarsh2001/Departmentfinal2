package com.harshproject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.jdbc.core.JdbcTemplate;

import com.harshproject.util.CustomHealthIndicator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class CustomHealthIndicatorTest {

    @Test
    void testDatabaseConnectionHealthy1() {
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        CustomHealthIndicator healthIndicator = new CustomHealthIndicator(jdbcTemplate);

        // Mock the behavior of the JdbcTemplate to return a result when the query is executed
        when(jdbcTemplate.queryForObject("SELECT 1 FROM dual", Integer.class)).thenReturn(1);

        Health health = healthIndicator.health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().containsKey("message"));
        assertEquals("Database connection is healthy", health.getDetails().get("message"));
    }
    
    @Test
    void testDatabaseConnectionUnhealthy1() {
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        CustomHealthIndicator healthIndicator = new CustomHealthIndicator(jdbcTemplate);

        // Mock the behavior of the JdbcTemplate to throw an exception when the query is executed
        when(jdbcTemplate.queryForObject("SELECT 1 FROM dual", Integer.class)).thenThrow(new RuntimeException("Database error"));

        Health health = healthIndicator.health();

        assertEquals(Status.DOWN, health.getStatus());
        assertTrue(health.getDetails().containsKey("message"));
        assertEquals("Database connection is not healthy", health.getDetails().get("message"));
    }
    
    @Test
    void testDatabaseConnectionHealthy() {
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        CustomHealthIndicator healthIndicator = new CustomHealthIndicator(jdbcTemplate);

        // Mock the behavior of the JdbcTemplate to return a result when the query is executed
        when(jdbcTemplate.queryForObject("SELECT 1 FROM dual", Integer.class)).thenReturn(1);

        Health health = healthIndicator.health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().containsKey("message"));
        assertEquals("Database connection is healthy", health.getDetails().get("message"));
    }

    @Test
    void testDatabaseConnectionUnhealthy() {
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        CustomHealthIndicator healthIndicator = new CustomHealthIndicator(jdbcTemplate);

        // Mock the behavior of the JdbcTemplate to throw an exception when the query is executed
        when(jdbcTemplate.queryForObject("SELECT 1 FROM dual", Integer.class)).thenThrow(new RuntimeException("Database error"));

        Health health = healthIndicator.health();

        assertEquals(Status.DOWN, health.getStatus());
        assertTrue(health.getDetails().containsKey("message"));
        assertEquals("Database connection is not healthy", health.getDetails().get("message"));
    }
   
}


package com.harshproject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import com.harshproject.controller.HealthController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class HealthControllerTest {

    @Mock
    private HealthIndicator customHealthIndicator;

    @InjectMocks
    private HealthController healthController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHealthStatusReturnsUp() {
        // Mock the health indicator response
        Health health = Health.up().build();
        when(customHealthIndicator.health()).thenReturn(health);

        // Call the controller method
        Health resultHealth = healthController.getHealthStatus();

        // Verify the response
        assertEquals(health, resultHealth);
    }

    @Test
    void testGetHealthStatusReturnsDown() {
        // Mock the health indicator response
        Health health = Health.down().build();
        when(customHealthIndicator.health()).thenReturn(health);

        // Call the controller method
        Health resultHealth = healthController.getHealthStatus();

        // Verify the response
        assertEquals(health, resultHealth);
    }
}

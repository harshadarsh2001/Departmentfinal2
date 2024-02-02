package com.harshproject.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator/health")
public class HealthController {

    private final HealthIndicator customHealthIndicator;

    public HealthController(HealthIndicator customHealthIndicator) {
        this.customHealthIndicator = customHealthIndicator;
    }

    @GetMapping
    public Health getHealthStatus() {
        return customHealthIndicator.health();
    }
}

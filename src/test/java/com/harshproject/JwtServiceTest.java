package com.harshproject;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;

import com.harshproject.service.JwtService;

import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@TestPropertySource(properties = {"jwt.secret=testSecret"})
class JwtServiceTest {

    private static final String USERNAME = "testUser";

    private final JwtService jwtService = new JwtService();

    @Test
    void testExtractUsername() {
        String token = jwtService.generateToken(USERNAME);
        String extractedUsername = jwtService.extractUsername(token);
        assertEquals(USERNAME, extractedUsername);
    }

    @Test
    void testExtractExpiration() {
        String token = jwtService.generateToken(USERNAME);
        Date expirationDate = jwtService.extractExpiration(token);
        assertNotNull(expirationDate);
    }

    @Test
    void testExtractClaim() {
        String token = jwtService.generateToken(USERNAME);
        String extractedUsername = jwtService.extractClaim(token, Claims::getSubject);
        assertEquals(USERNAME, extractedUsername);
    }


    @Test
    void testValidateToken() {
        UserDetails userDetails = new User(USERNAME, "password", new ArrayList<>());
        String validToken = jwtService.generateToken(USERNAME);
        assertTrue(jwtService.validateToken(validToken, userDetails));

        String invalidToken = jwtService.generateToken("differentUser");
        assertFalse(jwtService.validateToken(invalidToken, userDetails));
    }

    @Test
    void testGenerateToken() {
        String generatedToken = jwtService.generateToken(USERNAME);
        assertNotNull(generatedToken);
    }

    @Test
    void testGetSignKey() {
        Key signKey = jwtService.getSignKey();
        assertNotNull(signKey);
    }
}


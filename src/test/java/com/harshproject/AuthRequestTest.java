package com.harshproject;

import com.harshproject.entity.AuthRequest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuthRequestTest {

    @Test
    void testAuthRequestConstructorWithArguments() {
        AuthRequest authRequest = new AuthRequest("testUser", "testPassword");

        assertEquals("testUser", authRequest.getUsername());
        assertEquals("testPassword", authRequest.getPassword());
    }

    @Test
    void testAuthRequestNoArgsConstructor() {
        AuthRequest authRequest = new AuthRequest();

        assertNull(authRequest.getUsername());
        assertNull(authRequest.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        AuthRequest authRequest = new AuthRequest();

        authRequest.setUsername("newUser");
        authRequest.setPassword("newPassword");

        assertEquals("newUser", authRequest.getUsername());
        assertEquals("newPassword", authRequest.getPassword());
    }
    
    @Test
    void testEqualsAndHashCodeForNullFields() {
        AuthRequest authRequest1 = new AuthRequest();
        AuthRequest authRequest2 = new AuthRequest();

        assertEquals(authRequest1, authRequest2);
        assertEquals(authRequest1.hashCode(), authRequest2.hashCode());
    }

    @Test
    void testNotEqualsForDifferentUsernames() {
        AuthRequest authRequest1 = new AuthRequest("user1", "password");
        AuthRequest authRequest2 = new AuthRequest("user2", "password");

        assertNotEquals(authRequest1, authRequest2);
    }

    @Test
    void testNotEqualsForDifferentPasswords() {
        AuthRequest authRequest1 = new AuthRequest("user", "password1");
        AuthRequest authRequest2 = new AuthRequest("user", "password2");

        assertNotEquals(authRequest1, authRequest2);
    }

    // Add more test cases as needed for your specific requirements

    // Add more test cases as needed for your specific requirements

}

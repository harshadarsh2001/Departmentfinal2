package com.harshproject;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.harshproject.entity.UserInfo;
import com.harshproject.entity.UserInfoUserDetails;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserInfoUserDetailsTest {

    @Test
    void testConstructorWithValidUserInfo() {
        UserInfo userInfo = new UserInfo("testUser", "test@example.com", "password", "ROLE_USER,ROLE_ADMIN");

        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        assertEquals("testUser", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());

        List<GrantedAuthority> expectedAuthorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN")
        );
        assertEquals(expectedAuthorities, userDetails.getAuthorities());

        // Additional assertions for other methods if needed
        // ...

    }

    @Test
    void testConstructorWithEmptyRoles() {
        UserInfo userInfo = new UserInfo("testUser", "test@example.com", "password", "");

        UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo);

        assertEquals("testUser", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());

        List<GrantedAuthority> expectedAuthorities = Arrays.asList();
        assertEquals(expectedAuthorities, userDetails.getAuthorities());

        // Additional assertions for other methods if needed
        // ...
    }

    // Add more test cases as needed for your specific requirements

}

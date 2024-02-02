package com.harshproject;
import com.harshproject.entity.UserInfo;
import com.harshproject.repository.UserInfoRepository;
import com.harshproject.service.UserInfoUserDetailsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserInfoUserDetailsServiceTest {

    @Mock
    private UserInfoRepository repository;

    @InjectMocks
    private UserInfoUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        // Arrange
        String username = "testUser";
        UserInfo userInfo = new UserInfo(1, username, "test@example.com", "password", "ROLE_USER");
        when(repository.findByName(username)).thenReturn(Optional.of(userInfo));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(userInfo.getPassword(), userDetails.getPassword());
        assertEquals(userInfo.getRoles(), userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void loadUserByUsername_UserNotExists_ThrowsUsernameNotFoundException() {
        // Arrange
        String username = "nonExistingUser";
        when(repository.findByName(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
    }

    // Add more test cases as needed for your specific requirements

}

package com.harshproject;
import com.harshproject.filter.JwtAuthFilter;
import com.harshproject.service.JwtService;
import com.harshproject.service.UserInfoUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import java.io.IOException;

import static org.mockito.Mockito.*;

class JwtFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserInfoUserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doFilterInternal_ValidToken_ShouldAuthenticateUser() throws ServletException, IOException {
        // Arrange
        String token = "validToken";
        String username = "testUser";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        UserDetails userDetails = mock(UserDetails.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.extractUsername(token)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.validateToken(token, userDetails)).thenReturn(true);

        // Act
        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(userDetailsService).loadUserByUsername(username);
        verify(filterChain).doFilter(request, response);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        assertSecurityContextAuthentication(authToken);
    }

    // Add more test cases for different scenarios (invalid tokens, no token, etc.)

    private void assertSecurityContextAuthentication(UsernamePasswordAuthenticationToken expectedAuthToken) {
        Object authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication instanceof UsernamePasswordAuthenticationToken : "Authentication is not of expected type";
        UsernamePasswordAuthenticationToken actualAuthToken = (UsernamePasswordAuthenticationToken) authentication;
        assert actualAuthToken.getPrincipal().equals(expectedAuthToken.getPrincipal()) : "Principal mismatch";
        assert actualAuthToken.getAuthorities().equals(expectedAuthToken.getAuthorities()) : "Authorities mismatch";
        assert actualAuthToken.getDetails().equals(expectedAuthToken.getDetails()) : "Details mismatch";
    }
}


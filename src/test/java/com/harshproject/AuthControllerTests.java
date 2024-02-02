package com.harshproject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshproject.controller.AuthController;
import com.harshproject.entity.AuthRequest;
import com.harshproject.entity.UserInfo;
import com.harshproject.service.AuthService;
import com.harshproject.service.JwtService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private AuthController authController;

    private final MockMvc mockMvc;

    public AuthControllerTests() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void addNewUser_shouldReturnSuccessMessage_whenUserIsAddedSuccessfully() throws Exception {
        String successMessage = "User added successfully";

        // Mock the service call
        when(authService.addUser(any(UserInfo.class))).thenReturn(successMessage);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/new")
                .content("{" +
                        "\"username\": \"testUser\"," +
                        "\"password\": \"testPassword\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(successMessage));

        verify(authService, times(1)).addUser(any(UserInfo.class));
        verifyNoMoreInteractions(authService);
    }

    @Test
    void testAddNewUser_NullUserInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/new")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAuthenticateAndGetToken_NullAuthRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void testAddNewUser_ValidUserInfo() throws Exception {
        String successMessage = "User added successfully";

        // Mock the service call
        when(authService.addUser(any(UserInfo.class))).thenReturn(successMessage);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/new")
                .content("{" +
                        "\"username\": \"testUser\"," +
                        "\"password\": \"testPassword\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(successMessage));

        verify(authService, times(1)).addUser(any(UserInfo.class));
        verifyNoMoreInteractions(authService);
    }

    @Test
    void testAddNewUser_InvalidUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/new")
                .content("{" +
                        "\"username\": \"\"," +
                        "\"password\": \"testPassword\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    
}

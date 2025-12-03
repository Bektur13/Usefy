package com.bektur.controller;
import com.bektur.dto.LoginRequestDTO;
import com.bektur.dto.UserRegistrationDTO;
import com.bektur.model.User;
import com.bektur.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class AuthControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthController authController;

    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testRegister_Success() throws Exception {
        UserRegistrationDTO dto = new UserRegistrationDTO("Bektur", "secret");

        doNothing().when(userService).registerUser(any());

        mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated()).andExpect(content().string("User registered successfull"));

    }

    @Test
    void testLogin_Success() throws Exception {
        LoginRequestDTO dto = new LoginRequestDTO("Bektur", "password");

        User user = new User("Bektur", "hashed");

        when(userService.findByUsername("Bektur")).thenReturn(user);
        when(passwordEncoder.matches("Password", "hashed")).thenReturn(true);

        mockMvc.perform(post("/apu/auth/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk()).andExpect(content().string("Login successful!"));

    }

    @Test
    void testLogin_InvalidUsername() throws Exception {
        when(userService.findByUsername("wrong")).thenReturn(null);

        LoginRequestDTO dto = new LoginRequestDTO("wrong", "123");

        mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isUnauthorized());

    }

    @Test
    void testLogin_InvalidPassword() throws Exception {
        User user = new User("Bektur", "hashed");

        when(userService.findByUsername("Bektur")).thenReturn(user);
        when(passwordEncoder.matches("pass", "hashed")).thenReturn(false);

        LoginRequestDTO dto = new LoginRequestDTO("Bektur", "pass");

        mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andExpect(status().isUnauthorized());

    }

    @Test
    void bad_Request() throws  Exception {

    }


}

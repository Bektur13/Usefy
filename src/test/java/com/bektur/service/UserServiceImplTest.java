package com.bektur.service;

import com.bektur.dto.UserRegistrationDTO;
import com.bektur.model.User;
import com.bektur.repository.UserRepository;
import com.bektur.service.UserSeviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserSeviceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ShouldSaveUser_WhenNewUsername() {
        UserRegistrationDTO dto = new UserRegistrationDTO("Bektur", "pass123");

        when(userRepository.findByUserName("Bektur")).thenReturn(false);
        when(passwordEncoder.encode("pass123")).thenReturn("hashed123");

        User savedUser = new User("Bektur", "hashed123");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.registerUser(dto);

        assertEquals("Bektur", result.getUsername());
        assertEquals("hashed123", result.getPassword());
    }

    @Test
    void registerUser_ShouldThrowException_WhenUsernameExists() {
        UserRegistrationDTO dto = new UserRegistrationDTO("Bektur", "pass123");
        when(userRepository.findByUserName("bektur")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(dto));
    }

}


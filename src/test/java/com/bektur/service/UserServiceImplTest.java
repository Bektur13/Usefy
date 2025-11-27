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
    void testRegisterUser_Success() {
        UserRegistrationDTO dto = new UserRegistrationDTO("Bektur", "pass123");

        when(userRepository.findByUserName("Bektur")).thenReturn(null);
        when(passwordEncoder.encode("pass123")).thenReturn("hashed123");

        User savedUser = new User("Bektur", "hashed123");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.registerUser(dto);

        assertEquals("Bektur", result.getUsername());
        assertEquals("hashed123", result.getPassword());
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists() {
        UserRegistrationDTO dto = new UserRegistrationDTO("Bektur", "pass123");
        when(userRepository.findByUserName("Bektur")).thenReturn(new User());

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(dto));
    }

    @Test
    void testFindByUsername() {
        User user = new User("Bektur", "");

        when(userRepository.findByUserName("Bektur")).thenReturn(user);

        User result = userService.findByUsername("Bektur");

        assertEquals("Bektur", result.getUsername());
    }

    @Test
    void testFindByUsername_NotFound() {
        when(userRepository.findByUserName("unknown")).thenReturn(null);

        User result = userService.findByUsername("unknown");

        assertNull(result);
    }

}


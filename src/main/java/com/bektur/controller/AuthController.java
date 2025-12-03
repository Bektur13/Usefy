package com.bektur.controller;

import com.bektur.dto.LoginRequestDTO;
import com.bektur.dto.UserRegistrationDTO;
import com.bektur.model.User;
import com.bektur.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping( value = "/register")
    public ResponseEntity<?> register(@Valid @ModelAttribute UserRegistrationDTO dto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        userService.registerUser((dto));
        return ResponseEntity.status(201).body("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        User user = userService.findByUsername(dto.getUsername());

        if(user == null) {
            return ResponseEntity.status(401).body("Invalid username");
        }

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        return ResponseEntity.ok("Login successful!");
    }

}

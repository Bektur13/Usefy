package com.bektur.controller;

import com.bektur.model.User;
import com.bektur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User userData) {
        try {
            User registeredUser = userService.registerUser((userData));

            return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
        } catch(Exception e) {
            String errorMessage = "User can't be registered";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if(username == null || password == null) {
            return new ResponseEntity<>("Username and password are required.", HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUserName(username);

        if(user == null) {
            return new ResponseEntity<>("Invalid credentials.", HttpStatus.UNAUTHORIZED);
        }

        if(passwordEncoder.matches(password, user.getPasswordHash())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful.");
            response.put("username", user.getUsername());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid credentials: ", HttpStatus.UNAUTHORIZED);
        }
    }

}

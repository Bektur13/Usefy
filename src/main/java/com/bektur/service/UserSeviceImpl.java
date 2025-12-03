package com.bektur.service;


import com.bektur.dto.UserRegistrationDTO;
import com.bektur.model.User;
import com.bektur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSeviceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserSeviceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserRegistrationDTO dto) {
        if(userRepository.findByUserName(dto.getUsername()) != null) {
            throw new RuntimeException("Username already taken");
        }

        User user = new User("", "");
        user.setUsername(dto.getUsername());

        String hashed = passwordEncoder.encode(dto.getPassword());
        user.setPassword(hashed);

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
}

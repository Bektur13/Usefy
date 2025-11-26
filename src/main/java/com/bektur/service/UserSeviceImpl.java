package com.bektur.service;


import com.bektur.dto.UserRegistrationDTO;
import com.bektur.model.User;
import com.bektur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSeviceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public User registerUser(UserRegistrationDTO userData) {
        if(userRepository.findByUserName(userData.getUserName()) != null) {
            throw new RuntimeException("Username already exists");
        }

        String hashedPassword = passwordEncoder.encode(userData.getPassword());

        User newUser = new User(userRepository.findByUserName(userData.getUsername()));
        newUser.setUsername(userData.getUsername());
        newUser.setPasswordHash(hashedPassword);

        return userRepository.save(newUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName((username));
    }

}

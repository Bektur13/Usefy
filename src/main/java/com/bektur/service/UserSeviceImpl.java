package com.bektur.service;


import com.bektur.model.User;
import com.bektur.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSeviceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSeviceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User userData) {

        String hashedPassword = passwordEncoder.encode(userData.getPasswordHash());

        User newUser = new User();
        newUser.setUsername(userData.getUsername());
        newUser.setPasswordHash(hashedPassword);
        return userRepository.save(newUser);

    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findById(username);
    }

}

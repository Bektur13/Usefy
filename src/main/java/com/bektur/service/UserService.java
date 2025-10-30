package com.bektur.service;

import com.bektur.model.User;
import com.bektur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

public interface UserService {

    // This method takes user data and create new User.
    User registerUser(User userData);

    // A method retrieve a user from the database.
    User findByUserName(String username);
}
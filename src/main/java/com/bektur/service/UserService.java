package com.bektur.service;


import com.bektur.dto.UserRegistrationDTO;
import com.bektur.model.User;

public interface UserService {
    User registerUser(UserRegistrationDTO request);
    User findByUsername(String username);

//    boolean verifyUser(String username, String password);
}
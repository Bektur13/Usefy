package com.bektur.service;

import com.bektur.model.User;
import com.bektur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;



    public User register(User user) {
        user.setPasswordHash();
        return repo.save(user);
    }
}

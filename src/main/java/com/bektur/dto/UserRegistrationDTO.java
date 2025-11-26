package com.bektur.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String passwordHash;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public String getPassword() {
        return this.passwordHash;
    }
}

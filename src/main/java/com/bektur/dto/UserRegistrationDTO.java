package com.bektur.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String passwordHash;
}

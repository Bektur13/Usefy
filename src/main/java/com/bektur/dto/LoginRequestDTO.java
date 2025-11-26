package com.bektur.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String passwordHash;
}

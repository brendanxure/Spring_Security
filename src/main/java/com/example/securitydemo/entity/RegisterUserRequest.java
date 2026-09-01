package com.example.securitydemo.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
    private Role role;
}

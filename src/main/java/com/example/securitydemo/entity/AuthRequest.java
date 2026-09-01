package com.example.securitydemo.entity;

import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@Data
public class AuthRequest {
    private String username;
    private String password;
}

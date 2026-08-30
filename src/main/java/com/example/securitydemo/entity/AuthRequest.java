package com.example.securitydemo.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;
}

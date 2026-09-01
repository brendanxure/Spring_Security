package com.example.securitydemo.controller;

import com.example.securitydemo.entity.RegisterUserRequest;
import com.example.securitydemo.entity.Role;
import com.example.securitydemo.entity.UserResponse;
import com.example.securitydemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        registerUserRequest.setRole(Role.USER);
        UserResponse newUser =  userService.registerUser(registerUserRequest);
        return ResponseEntity.ok(newUser);
    }
}

package com.example.securitydemo.service;

import com.example.securitydemo.entity.RegisterUserRequest;
import com.example.securitydemo.entity.UserResponse;
import com.example.securitydemo.entity.Users;
import com.example.securitydemo.repository.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(RegisterUserRequest registerUserRequest){
        if(userDetailsRepository.findByUsername(registerUserRequest.getUsername()).isPresent()){
            throw new RuntimeException("User already exist");
        }
        Users user = new Users();
        user.setUsername(registerUserRequest.getUsername());
        user.setRole(registerUserRequest.getRole());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));

        Users savedUser =  userDetailsRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getRole().name());
    }
}

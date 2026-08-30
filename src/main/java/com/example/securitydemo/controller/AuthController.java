package com.example.securitydemo.controller;

import com.example.securitydemo.entity.AuthRequest;
import com.example.securitydemo.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public String generateToken (@RequestBody AuthRequest authRequest){
       try{
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
           return jwtUtil.generateToken(authRequest.getUsername());
       }catch (Exception e){
            throw e;
       }
    }

    @GetMapping("/health")
    public String home(){
        return "Healthy";
    }
}

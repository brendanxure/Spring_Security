package com.example.securitydemo.controller;

import com.example.securitydemo.entity.AuthRequest;
import com.example.securitydemo.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/health")
    public String postHome(){
        return "Post Authorized correctly";
    }

    @DeleteMapping("/health")
    public String deleteHome(){
        return "Delete Authorized successfully";
    }
}

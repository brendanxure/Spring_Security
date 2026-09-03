package com.example.securitydemo.controller;

import com.example.securitydemo.entity.AuthRequest;
import com.example.securitydemo.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public String generateToken (@RequestBody AuthRequest authRequest){
       try{
           System.out.println("🔥 AUTHENTICATE CONTROLLER REACHED");
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
           return jwtUtil.generateToken(authRequest.getUsername());
       }catch (Exception e){
            throw e;
       }
    }

    @GetMapping("/admin-test")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminTest() {
        return "You are an ADMIN";
    }

    @GetMapping("/user-test")
    @PreAuthorize("hasRole('USER')")
    public String userTest() {
        return "You are a USER";
    }

    @GetMapping("/health")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String home(){
        return "Healthy";
    }

    @PostMapping("/health")
    @PreAuthorize("hasAuthority('WEATHER_WRITE')")
    public String postHome(){
        return "Post Authorized correctly";
    }

    @DeleteMapping("/health")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteHome(){
        return "Delete Authorized successfully";
    }

    // This method is usually used in services but can be used in controller
    @GetMapping("/healthAdmin")
    @PostAuthorize("returnObject.username == authentication.name")
    public AuthRequest testPostAuthorized(){
        return new AuthRequest("admin", "1234");
    }

    // This method is used in services too to filter unwanted results from a collection
    @GetMapping("/healthAdmins")
    @PostFilter("filterObject.username == authentication.name")
    public Collection<AuthRequest> testFilterAuthorized() {
        return List.of(new AuthRequest("admin", "1234"),
                        new AuthRequest("user", "1234"));
    }
}

package com.example.securitydemo.util;

import com.example.securitydemo.entity.Users;
import com.example.securitydemo.repository.UserDetailsRepository;
import com.example.securitydemo.util.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final UserDetailsRepository userDetailsRepository;

    public CustomOAuth2SuccessHandler(
            JWTUtil jwtUtil,
            UserDetailsRepository userDetailsRepository) {

        this.jwtUtil = jwtUtil;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        String email = oidcUser.getEmail();

        Users user = userDetailsRepository
                .findByUsername(email)
                .orElseThrow();

        String token = jwtUtil.generateToken(user.getUsername());

        System.out.println("JWT FROM GOOGLE: " + token);

        response.getWriter().write(token);
    }
}
package com.example.securitydemo.service;

import com.example.securitydemo.entity.Users;
import com.example.securitydemo.repository.UserDetailsRepository;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends OidcUserService {

    private final UserDetailsRepository userDetailsRepository;

    public CustomOidcUserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest)
            throws OAuth2AuthenticationException {

        OidcUser oidcUser = super.loadUser(userRequest);

        String googleId = oidcUser.getSubject();
        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();
        String picture = oidcUser.getPicture();

        System.out.println("Google ID: " + googleId);
        System.out.println("Email: " + email);
        System.out.println("Name: " + name);
        System.out.println("Picture: " + picture);

        Users existingUser =
                ;

        if (userDetailsRepository.findByGoogleId(googleId).isPresent()) {
            return oidcUser;
        }

        Users newUser = new Users();

        newUser.setUsername(email);
        newUser.setPassword(null);
        newUser.setGoogleId(googleId);
        newUser.setRole(Role.USER);

        userDetailsRepository.save(newUser);

        return oidcUser;
    }
}

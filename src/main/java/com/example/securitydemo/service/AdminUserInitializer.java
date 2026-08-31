package com.example.securitydemo.service;

import com.example.securitydemo.entity.Role;
import com.example.securitydemo.entity.Users;
import com.example.securitydemo.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {
    @Bean
    public CommandLineRunner createAdminUser(
            UserDetailsRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {

                Users admin = new Users();

                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);
                System.out.println("Default admin user created");
            }

            if (userRepository.findByUsername("user").isEmpty()) {

                Users admin = new Users();

                admin.setUsername("user");
                admin.setPassword(passwordEncoder.encode("user123"));
                admin.setRole(Role.USER);

                userRepository.save(admin);
                System.out.println("Default user created");
            }
        };
    }
}

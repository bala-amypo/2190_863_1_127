package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import java.util.Collections;

public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserDetails() {
            @Override
            public String getUsername() {
                return user.getEmail();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public java.util.Collection<String> getAuthorities() {
                return Collections.singleton("ROLE_" + user.getRole().name());
            }
        };
    }
}

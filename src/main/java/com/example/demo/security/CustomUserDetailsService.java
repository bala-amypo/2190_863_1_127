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

        return new UserDetails(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList("ROLE_" + user.getRole().name())
        );
    }
}

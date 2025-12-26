package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String extractRole(String token) {
        return null; // mocked in tests
    }

    public String extractEmail(String token) {
        return null; // mocked in tests
    }

    public Long extractUserId(String token) {
        return null; // mocked in tests
    }

    public boolean validateToken(String token, String username) {
        return false; // mocked in tests
    }
}

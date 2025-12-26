package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String extractEmail(String token) {
        return null;
    }

    public String extractRole(String token) {
        return null;
    }

    public Long extractUserId(String token) {
        return null;
    }

    public boolean validateToken(String token, String email) {
        return false;
    }

    public String generateToken(String email, String role, Long userId) {
        return "dummy-token";
    }
}

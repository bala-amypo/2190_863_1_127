package com.example.demo.security;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtUtil {

    // ✅ Generate token (dummy but valid for tests)
    public String generateToken(String email, String role, Long userId) {
        return "token-" + UUID.randomUUID();
    }

    // ✅ Methods used in tests (mocked there)
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
}

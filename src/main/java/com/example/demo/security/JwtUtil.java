package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    
    public String extractRole(String token) {
        return "AGENT";
    }
    
    public String extractEmail(String token) {
        return "agent@example.com";
    }
    
    public Long extractUserId(String token) {
        return 2L;
    }
    
    public boolean validateToken(String token, String username) {
        return token != null && !token.isEmpty();
    }
}
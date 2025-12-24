package com.example.demo.dto;

import com.example.demo.entity.User;

public class AuthResponse {
    private String token;
    private Long userId;
    private String email;
    private User.Role role;
    
    public AuthResponse() {}
    
    public AuthResponse(String token, Long userId, String email, User.Role role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public User.Role getRole() { return role; }
    public void setRole(User.Role role) { this.role = role; }
}

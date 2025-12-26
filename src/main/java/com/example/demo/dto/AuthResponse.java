package com.example.demo.dto;

public class AuthResponse {

    private String message;
    private String email;
    private Long userId;
    private String role;

    // Default constructor
    public AuthResponse() {
    }

    // Full constructor
    public AuthResponse(String message, String email, Long userId, String role) {
        this.message = message;
        this.email = email;
        this.userId = userId;
        this.role = role;
    }

    // Single-argument constructor for simple messages
    public AuthResponse(String message) {
        this.message = message;
    }

    // Getters and setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

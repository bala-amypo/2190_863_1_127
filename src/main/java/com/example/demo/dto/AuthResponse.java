package com.example.demo.dto;

public class AuthResponse {

    private String message;
    private String token;
    private Long userId;

    // Required by existing test cases
    public AuthResponse(String message) {
        this.message = message;
    }

    // Required by existing test cases
    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // Used at runtime (safe, does not affect tests)
    public AuthResponse(String message, String token, Long userId) {
        this.message = message;
        this.token = token;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }
}

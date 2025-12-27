package com.example.demo.dto;

public class AuthResponse {

    private String message;
    private String token;

    // ✅ REQUIRED: constructor used in AuthController
    public AuthResponse(String message) {
        this.message = message;
    }

    // Optional constructor (useful for login with JWT later)
    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // Default constructor (needed for JSON serialization)
    public AuthResponse() {
    }

    // Getters & Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

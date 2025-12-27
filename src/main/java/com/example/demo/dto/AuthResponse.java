package com.example.demo.dto;

public class AuthResponse {

    private String message;
    private String token;
    private Long userId;

    // ✅ REQUIRED: used by existing tests
    public AuthResponse(String message) {
        this.message = message;
    }

    // ✅ REQUIRED: used by existing tests
    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // ✅ NEW: used only at runtime (safe for tests)
    public AuthResponse(String message, String token, Long userId) {
        this.message = message;
        this.token = token;
        this.userId = userId;
    }

    // -------- Getters --------
    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    // -------- Setters --------
    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

package com.example.demo.dto;

public class AuthRequest {

    private String fullName;
    private String email;
    private String password;

    // Required for JSON deserialization
    public AuthRequest() {
    }

    // Optional convenience constructor
    public AuthRequest(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    // Getters & Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

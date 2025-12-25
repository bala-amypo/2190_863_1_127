package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public User register(@RequestBody AuthRequest request) {
        return userService.registerCustomer(request.getEmail(), request.getEmail(), request.getPassword());
    }
    
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userService.findByEmail(request.getEmail());
        AuthResponse response = new AuthResponse();
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        response.setToken("mock-jwt-token");
        return response;
    }
}
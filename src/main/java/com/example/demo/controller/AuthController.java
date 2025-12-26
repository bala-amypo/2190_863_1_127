package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {
        User user = userService.registerCustomer(
                request.getFullName(),
                request.getEmail(),
                request.getPassword()
        );
        return new AuthResponse(
                "User registered successfully",
                user.getEmail(),
                user.getId(),
                user.getRole().name()
        );
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        // login logic here
        User user = userService.findByEmail(request.getEmail());
        return new AuthResponse(
                "Login successful",
                user.getEmail(),
                user.getId(),
                user.getRole().name()
        );
    }
}

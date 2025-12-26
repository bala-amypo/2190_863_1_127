package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // Dummy response for college project
        return new AuthResponse(
                "dummy-jwt-token",
                "CUSTOMER",
                request.getEmail(),
                1L
        );
    }
}

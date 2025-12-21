package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        User user = userService.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("Bad credentials");
        }

        return "Login successful for " + email;
    }
}

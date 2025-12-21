// AuthController.java
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password) {
        return userService.registerCustomer(name, email, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UnauthorizedException("Invalid credentials");
        }
        return "Login successful for " + email;
    }
}

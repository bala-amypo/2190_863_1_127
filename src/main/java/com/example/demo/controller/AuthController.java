package com.example.demo.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController{

    @Autowired
    UserService service;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return service.registerUser(user);
    }

    @PostMapping("/login")
    public User loginuser(@RequestBody User user){
        return service.loginUser(user);
    }
}

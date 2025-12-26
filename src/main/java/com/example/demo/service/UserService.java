package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    // ===== EXISTING METHODS (kept) =====
    User registerUser(User user);

    User loginUser(User user);

    // ===== TEST-REQUIRED METHODS (added) =====
    User registerCustomer(String name, String email, String password);

    User findByEmail(String email);
}

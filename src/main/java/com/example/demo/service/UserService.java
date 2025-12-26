package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User registerCustomer(String fullName, String email, String password);
    User findByEmail(String email);
}

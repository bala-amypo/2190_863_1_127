package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    User registerCustomer(String fullName, String email, String password);

    User findByEmail(String email);

    User findById(Long id);

    User login(String email, String password);
}

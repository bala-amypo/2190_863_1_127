package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User registerCustomer(String name, String email, String password) {

        if (repo.findByEmail(email).isPresent()) {
            throw new BadRequestException("email already exists");
        }

        User u = new User();
        u.setFullName(name);
        u.setEmail(email);

        // NO encryption (since security is removed)
        u.setPassword(password);

        u.setRole(User.Role.CUSTOMER);
        return repo.save(u);
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}

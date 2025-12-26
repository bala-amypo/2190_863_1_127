package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    // ===== Constructor REQUIRED by tests =====
    public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    // ===== TEST-EXPECTED METHOD =====
    @Override
    public User registerCustomer(String name, String email, String password) {

        if (repo.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(User.Role.CUSTOMER);

        return repo.save(user);
    }

    // ===== TEST-EXPECTED METHOD =====
    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ===== YOUR EXISTING METHOD (kept) =====
    @Override
    public User registerUser(User user) {
        user.setId(null);
        try {
            return repo.save(user);
        } catch (Exception e) {
            throw new RuntimeException("This Email has already been registered");
        }
    }

    // ===== YOUR EXISTING METHOD (kept) =====
    @Override
    public User loginUser(User user) {

        Optional<User> dbUser = repo.findByEmail(user.getEmail());
        if (dbUser.isEmpty()) {
            throw new RuntimeException("Email not found!");
        }

        User existingUser = dbUser.get();

        // NOTE: legacy login (plain password)
        if (!existingUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid Password!");
        }

        return existingUser;
    }
}

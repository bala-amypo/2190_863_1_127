package com.example.demo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "users")  
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    // ===== Existing field (kept) =====
    private String name;

    // ===== Alias for tests =====
    @Transient
    private String fullName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(hidden = true)
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        USER,
        AGENT,
        ADMIN,
        CUSTOMER
    }

    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = Role.CUSTOMER;
        }
        if (this.name == null && this.fullName != null) {
            this.name = this.fullName;
        }
    }

    // ===== Constructors =====
    public User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.fullName = name;
        this.email = email;
        this.password = password;
        this.role = Role.CUSTOMER;
    }

    // ===== Getters =====
    public Long getId() { return id; }

    public String getEmail() { return email; }

    public String getName() { return name; }

    // === Test expected ===
    public String getFullName() {
        return fullName != null ? fullName : name;
    }

    public String getPassword() { return password; }

    public Role getRole() { return role; }

    // ===== Setters =====
    public void setId(Long id) { this.id = id; }

    public void setEmail(String email) { this.email = email; }

    public void setName(String name) {
        this.name = name;
        this.fullName = name;
    }

    // === Test expected ===
    public void setFullName(String fullName) {
        this.fullName = fullName;
        this.name = fullName;
    }

    public void setPassword(String password) { this.password = password; }

    public void setRole(Role role) { this.role = role; }
}

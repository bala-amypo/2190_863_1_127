package com.example.demo.entity;

import jakarta.persistance.*;

@Entity
@Table(name="users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private String name;

    @Column(unique = true) private String email;

    private Sting password;

    private String role;
}
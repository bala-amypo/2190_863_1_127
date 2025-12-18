package com.example.demo.entity;

import jakarta.persistance.*;

@Entity
@Table(name="users")
public class User{

    @Id
    @GeneratedValue(strategy)
}
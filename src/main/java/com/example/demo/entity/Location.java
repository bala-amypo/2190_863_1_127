package com.exampke.demo.entity;

import jakarta.persistence.*;

@Entity
public class Location{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Deouble latitude;
    private Double longitude;
}
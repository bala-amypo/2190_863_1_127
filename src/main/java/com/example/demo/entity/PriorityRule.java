package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PriorityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String description;
    private int weight;
    private boolean active = true;

    @ManyToMany(mappedBy = "priorityRules")
    private Set<Complaint> complaints = new HashSet<>();

public int getWeight() {
    return weight;
}

    // getters & setters
}

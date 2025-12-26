package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "priority_rules")
@Data
public class PriorityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleName;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToMany(mappedBy = "priorityRules")
    private Set<Complaint> complaints = new HashSet<>();
}
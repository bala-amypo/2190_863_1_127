package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Complaint {

    public enum Status { NEW, OPEN, IN_PROGRESS, RESOLVED }
    public enum Severity { LOW, MEDIUM, HIGH, CRITICAL }
    public enum Urgency { LOW, MEDIUM, HIGH, IMMEDIATE }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private String channel;

    private Integer priorityScore;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    private Urgency urgency;

    private LocalDateTime createdAt;

    @ManyToOne
    private User customer;

    @ManyToOne
    private User assignedAgent;

    @ManyToMany
    private Set<PriorityRule> priorityRules = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters
    // (all standard – no logic)
    // generate via IDE
}

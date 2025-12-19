package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Complaint {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "priority_rule_id")
    private PriorityRule priorityRule;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public PriorityRule getPriorityRule() { return priorityRule; }
    public void setPriorityRule(PriorityRule priorityRule) { this.priorityRule = priorityRule; }
}

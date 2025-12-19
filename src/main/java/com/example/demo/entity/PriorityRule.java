package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class PriorityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String description;
    private Integer weight;

    private Boolean active = true;

    @ManyToMany(mappedBy = "priorityRules")
    private Set<Complaint> complaints;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getWeight() { return weight; }
    public void setWeight(Integer weight) { this.weight = weight; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Set<Complaint> getComplaints() { return complaints; }
    public void setComplaints(Set<Complaint> complaints) { this.complaints = complaints; }
}

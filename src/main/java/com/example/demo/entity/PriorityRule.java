package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class PriorityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    // ===== Existing fields (kept) =====
    private String category;
    private String description;
    private int baseScore;

    // ===== Required by tests =====
    private String ruleName;

    private int weight;

    private boolean active = true;

    @ManyToMany(mappedBy = "priorityRules")
    private Set<Complaint> complaints = new HashSet<>();

    // ===== Constructors =====
    public PriorityRule() {
        this.baseScore = 10;
        this.weight = this.baseScore; // keep logic consistent
        this.active = true;
    }

    // ===== Getters / Setters =====
    public Long getId() { return id; }

    // ---- ruleName ----
    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    // ---- category (legacy) ----
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    // ---- description ----
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // ---- weight (TEST EXPECTED) ----
    public int getWeight() { return weight; }
    public void setWeight(int weight) {
        this.weight = weight;
        this.baseScore = weight; // keep in sync
    }

    // ---- baseScore (legacy) ----
    public int getBaseScore() { return baseScore; }
    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
        this.weight = baseScore;
    }

    // ---- active ----
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // ---- complaints ----
    public Set<Complaint> getComplaints() { return complaints; }
    public void setId(Long id) {
    this.id = id;
}

}

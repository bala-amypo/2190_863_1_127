package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private String channel;

    private Integer priorityScore;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonIgnore
    private LocalDateTime submittedOn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.NEW;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Urgency urgency;

    // ===== REQUIRED BY TESTS =====

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    private User assignedAgent;

    @ManyToMany
    private Set<PriorityRule> priorityRules = new HashSet<>();

    // ==============================

    public enum Status {
        NEW, IN_PROGRESS, RESOLVED, CLOSED
    }

    public enum Severity {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    public enum Urgency {
        LOW, MEDIUM, HIGH, IMMEDIATE
    }

    @PrePersist
    public void onCreate() {
        this.submittedOn = LocalDateTime.now();
        if (this.status == null) {
            this.status = Status.NEW;
        }
        if (this.severity == null) {
            this.severity = Severity.LOW;
        }
        if (this.urgency == null) {
            this.urgency = Urgency.LOW;
        }
        if (this.priorityScore == null) {
            this.priorityScore = 0;
        }
    }

    // ===== Getters & Setters =====

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public Integer getPriorityScore() { return priorityScore; }
    public void setPriorityScore(Integer priorityScore) { this.priorityScore = priorityScore; }

    public LocalDateTime getSubmittedOn() { return submittedOn; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Severity getSeverity() { return severity; }
    public void setSeverity(Severity severity) { this.severity = severity; }

    public Urgency getUrgency() { return urgency; }
    public void setUrgency(Urgency urgency) { this.urgency = urgency; }

    // === Test-expected names ===
    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }

    public User getAssignedAgent() { return assignedAgent; }
    public void setAssignedAgent(User assignedAgent) { this.assignedAgent = assignedAgent; }

    public Set<PriorityRule> getPriorityRules() { return priorityRules; }
public void setId(Long id) {
    this.id = id;
}

    // ===== Constructors =====

    public Complaint() {}

    public Complaint(String title, String description, String category,
                     Integer priorityScore, Severity severity,
                     User customer, Urgency urgency) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priorityScore = priorityScore;
        this.severity = severity;
        this.urgency = urgency;
        this.customer = customer;
        this.status = Status.NEW;
    }
}

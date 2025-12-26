package com.example.demo.dto;

import com.example.demo.entity.Complaint;
import java.time.LocalDateTime;

public class ComplaintResponse {
    private Long id;
    private String title;
    private String description;
    private Complaint.Severity severity;
    private Complaint.Urgency urgency;
    private Complaint.Status status;
    private Integer priorityScore;
    private LocalDateTime createdAt;

    public ComplaintResponse() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Complaint.Severity getSeverity() { return severity; }
    public void setSeverity(Complaint.Severity severity) { this.severity = severity; }
    public Complaint.Urgency getUrgency() { return urgency; }
    public void setUrgency(Complaint.Urgency urgency) { this.urgency = urgency; }
    public Complaint.Status getStatus() { return status; }
    public void setStatus(Complaint.Status status) { this.status = status; }
    public Integer getPriorityScore() { return priorityScore; }
    public void setPriorityScore(Integer priorityScore) { this.priorityScore = priorityScore; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

package com.example.demo.dto;

import com.example.demo.entity.Complaint;
import java.time.LocalDateTime;

public class ComplaintResponse {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String channel;
    private Integer priorityScore;
    private LocalDateTime createdAt;
    private String status;
    private String severity;
    private String urgency;
    private String customerEmail;
    
    public ComplaintResponse(Complaint complaint) {
        this.id = complaint.getId();
        this.title = complaint.getTitle();
        this.description = complaint.getDescription();
        this.category = complaint.getCategory();
        this.channel = complaint.getChannel();
        this.priorityScore = complaint.getPriorityScore();
        this.createdAt = complaint.getCreatedAt();
        this.status = complaint.getStatus() != null ? complaint.getStatus().name() : null;
        this.severity = complaint.getSeverity() != null ? complaint.getSeverity().name() : null;
        this.urgency = complaint.getUrgency() != null ? complaint.getUrgency().name() : null;
        this.customerEmail = complaint.getCustomer() != null ? complaint.getCustomer().getEmail() : null;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
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
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }
    
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
}
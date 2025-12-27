package com.example.demo.dto;

import com.example.demo.entity.Complaint;

public class ComplaintResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String channel;
    private Complaint.Severity severity;
    private Complaint.Urgency urgency;
    private Complaint.Status status;
    private Integer priorityScore;

    public ComplaintResponse() {
    }

    public ComplaintResponse(Complaint complaint) {
        this.id = complaint.getId();
        this.title = complaint.getTitle();
        this.description = complaint.getDescription();
        this.category = complaint.getCategory();
        this.channel = complaint.getChannel();
        this.severity = complaint.getSeverity();
        this.urgency = complaint.getUrgency();
        this.status = complaint.getStatus();
        this.priorityScore = complaint.getPriorityScore();
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Complaint.Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Complaint.Severity severity) {
        this.severity = severity;
    }

    public Complaint.Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Complaint.Urgency urgency) {
        this.urgency = urgency;
    }

    public Complaint.Status getStatus() {
        return status;
    }

    public void setStatus(Complaint.Status status) {
        this.status = status;
    }

    public Integer getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(Integer priorityScore) {
        this.priorityScore = priorityScore;
    }
}

package com.example.demo.dto;

import com.example.demo.entity.Complaint;

public class ComplaintResponse {

    private Long id;
    private String title;
    private Complaint.Status status;
    private Integer priorityScore;

    public ComplaintResponse() {
    }

    public ComplaintResponse(Long id, String title, Complaint.Status status, Integer priorityScore) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.priorityScore = priorityScore;
    }

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

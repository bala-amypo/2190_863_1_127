package com.example.demo.dto;

import com.example.demo.entity.Complaint;

public class ComplaintResponse {
    private Long id;
    private String title;
    private String description;
    private Complaint.Status status;
    private Integer priorityScore;

    // getters and setters
}

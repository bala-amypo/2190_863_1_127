package com.example.demo.dto;

import com.example.demo.entity.Complaint;
import lombok.Data;

@Data
public class ComplaintRequest {
    private String title;
    private String description;
    private String category;
    private String channel;
    private Complaint.Severity severity;
    private Complaint.Urgency urgency;
}
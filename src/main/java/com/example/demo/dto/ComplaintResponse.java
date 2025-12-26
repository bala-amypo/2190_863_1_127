package com.example.demo.dto;

import com.example.demo.entity.Complaint;
import lombok.Data;
import java.time.LocalDateTime;

@Data
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
    private String customerEmail;
    private String assignedAgentEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
}

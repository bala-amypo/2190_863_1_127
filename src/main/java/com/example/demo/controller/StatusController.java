package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    
    @GetMapping("/complaint-statuses")
    public Complaint.Status[] getComplaintStatuses() {
        return Complaint.Status.values();
    }
    
    @GetMapping("/severities")
    public Complaint.Severity[] getSeverities() {
        return Complaint.Severity.values();
    }
    
    @GetMapping("/urgencies")
    public Complaint.Urgency[] getUrgencies() {
        return Complaint.Urgency.values();
    }
}
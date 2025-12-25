package com.example.demo.dto;

import com.example.demo.entity.Complaint.Severity;
import com.example.demo.entity.Complaint.Urgency;

public class ComplaintRequest {

    private String title;
    private String description;
    private String category;
    private String channel;
    private Severity severity;
    private Urgency urgency;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getChannel() {
        return channel;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Urgency getUrgency() {
        return urgency;
    }
}

package com.example.demo.dto;

import com.example.demo.entity.Complaint;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComplaintResponse {
    private Long id;
    private String title;
    private Complaint.Status status;
    private Integer priorityScore;
}

package com.example.demo.service;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import java.util.List;

public interface ComplaintService {
    Complaint createComplaint(ComplaintRequest request);
    List<Complaint> getAllComplaints();
    Complaint getComplaintById(Long id);
}

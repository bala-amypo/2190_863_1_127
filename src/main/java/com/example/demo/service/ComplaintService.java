package com.example.demo.service;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import java.util.List;

public interface ComplaintService {
    Complaint submitComplaint(Complaint complaint);
    List<Complaint> getComplaintsForUser(User user);
    List<Complaint> getPrioritizedComplaints();
    Complaint getComplaintById(Long id);
    Complaint updateComplaint(Complaint complaint);
}

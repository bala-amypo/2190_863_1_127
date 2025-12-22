package com.example.demo.service;

import com.example.demo.entity.Complaint;
import java.util.List;

public interface ComplaintService {

    Complaint submitComplaint(Complaint complaint);

    List<Complaint> getAllComplaints();

    List<Complaint> getPrioritizedComplaints();

    Complaint getComplaintById(Long id);

    Complaint updateComplaint(Complaint complaint);
}

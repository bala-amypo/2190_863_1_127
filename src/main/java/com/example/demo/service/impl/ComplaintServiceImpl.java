package com.example.demo.service.impl;

import com.example.demo.model.Complaint;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.PriorityRuleService;

import java.util.List;

public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final PriorityRuleService priorityRuleService;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                PriorityRuleService priorityRuleService) {
        this.complaintRepository = complaintRepository;
        this.priorityRuleService = priorityRuleService;
    }

    // POST -> create new complaint
    @Override
    public Complaint saveComplaint(Complaint complaint) {
        complaint.setPriority(priorityRuleService.calculatePriority(complaint));
        return complaintRepository.save(complaint);
    }

    // GET -> get all complaints
    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // GET -> get complaint by id
    @Override
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id).orElse(null);
    }

    // PUT -> update existing complaint
    @Override
    public Complaint updateComplaint(Complaint complaint) {
        if (complaint.getId() == null || !complaintRepository.existsById(complaint.getId())) {
            throw new IllegalArgumentException("Complaint does not exist");
        }
        complaint.setPriority(priorityRuleService.calculatePriority(complaint));
        return complaintRepository.save(complaint);
    }
}

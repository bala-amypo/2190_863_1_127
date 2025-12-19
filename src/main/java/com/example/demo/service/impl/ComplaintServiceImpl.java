package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.ComplaintService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final PriorityRuleRepository priorityRuleRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                PriorityRuleRepository priorityRuleRepository) {
        this.complaintRepository = complaintRepository;
        this.priorityRuleRepository = priorityRuleRepository;
    }

    @Override
    public Complaint createComplaint(ComplaintRequest request) {
        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());

        PriorityRule priority = priorityRuleRepository.findById(Long.parseLong(request.getPriority()))
                .orElse(null);
        complaint.setPriorityRule(priority);

        return complaintRepository.save(complaint);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id).orElse(null);
    }
}

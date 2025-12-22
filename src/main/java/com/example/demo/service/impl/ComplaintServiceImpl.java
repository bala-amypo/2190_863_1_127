package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;
    private final PriorityRuleService ruleService;

    public ComplaintServiceImpl(ComplaintRepository repo,
                                PriorityRuleService ruleService) {
        this.repo = repo;
        this.ruleService = ruleService;
    }

    @Override
    public Complaint submitComplaint(Complaint complaint) {

        if (complaint.getSeverity() == null || complaint.getUrgency() == null) {
            throw new IllegalArgumentException("Severity and Urgency must be provided");
        }

        int score = ruleService.computePriorityScore(complaint);
        complaint.setPriorityScore(score);

        return repo.save(complaint);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return repo.findAll();
    }

    @Override
    public List<Complaint> getPrioritizedComplaints() {
        return repo.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found with id: " + id));
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {

        Complaint existing = repo.findById(complaint.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found with id: " + complaint.getId()));

        if (complaint.getDescription() != null) {
            existing.setDescription(complaint.getDescription());
        }

        if (complaint.getStatus() != null) {
            existing.setStatus(complaint.getStatus());
        }

        if (existing.getSeverity() != null && existing.getUrgency() != null) {
            existing.setPriorityScore(
                    ruleService.computePriorityScore(existing)
            );
        }

        return repo.save(existing);
    }
}

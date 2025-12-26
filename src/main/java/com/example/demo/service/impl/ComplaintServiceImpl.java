package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.entity.User;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final PriorityRuleService priorityRuleService;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                PriorityRuleService priorityRuleService) {
        this.complaintRepository = complaintRepository;
        this.priorityRuleService = priorityRuleService;
    }

    @Override
    public Complaint submitComplaint(ComplaintRequest request, User customer) {
        Complaint c = new Complaint();
        c.setTitle(request.getTitle());
        c.setDescription(request.getDescription());
        c.setCategory(request.getCategory());
        c.setChannel(request.getChannel());
        c.setSeverity(request.getSeverity());
        c.setUrgency(request.getUrgency());
        c.setCustomer(customer);

        // Compute priority score using active rules
        c.setPriorityScore(priorityRuleService.computePriorityScore(c));

        return complaintRepository.save(c);
    }

    @Override
    public List<Complaint> getComplaintsForUser(User customer) {
        return complaintRepository.findByCustomer(customer);
    }

    @Override
    public List<Complaint> getPrioritizedComplaints() {
        return complaintRepository.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }

    @Override
    public Complaint updateStatus(Long complaintId, Complaint.Status status) {
        Optional<Complaint> optional = complaintRepository.findById(complaintId);
        if (optional.isPresent()) {
            Complaint c = optional.get();
            c.setStatus(status);
            return complaintRepository.save(c);
        }
        return null;
    }

    @Override
    public List<String> getStatusHistory(Long complaintId) {
        // Dummy history for now
        return List.of("NEW", "IN_PROGRESS", "RESOLVED");
    }
}

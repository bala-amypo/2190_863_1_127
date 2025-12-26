package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;
    private final PriorityRuleService ruleService;

    public ComplaintServiceImpl(
            ComplaintRepository repo,
            Object ignored1,
            Object ignored2,
            PriorityRuleService ruleService
    ) {
        this.repo = repo;
        this.ruleService = ruleService;
    }

    public Complaint submitComplaint(ComplaintRequest r, User user) {
        Complaint c = new Complaint();
        c.setTitle(r.getTitle());
        c.setDescription(r.getDescription());
        c.setCategory(r.getCategory());
        c.setChannel(r.getChannel());
        c.setSeverity(r.getSeverity());
        c.setUrgency(r.getUrgency());
        c.setCustomer(user);
        c.setPriorityScore(ruleService.computePriorityScore(c));
        return repo.save(c);
    }

    public List<Complaint> getComplaintsForUser(User user) {
        return repo.findByCustomer(user);
    }

    public List<Complaint> getPrioritizedComplaints() {
        return repo.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }
}

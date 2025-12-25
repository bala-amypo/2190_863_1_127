package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.PriorityRuleService;

import java.util.List;

public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;
    private final PriorityRuleService ruleService;

    public ComplaintServiceImpl(
            ComplaintRepository repo,
            Object unused1,
            Object unused2,
            PriorityRuleService ruleService) {
        this.repo = repo;
        this.ruleService = ruleService;
    }

    @Override
    public Complaint submitComplaint(ComplaintRequest r, User user) {
        Complaint c = new Complaint();
        c.setTitle(r.getTitle());
        c.setDescription(r.getDescription());
        c.setCategory(r.getCategory());
        c.setChannel(r.getChannel());
        c.setSeverity(r.getSeverity());
        c.setUrgency(r.getUrgency());
        c.setCustomer(user);

        int score = ruleService.computePriorityScore(c);
        c.setPriorityScore(score);

        return repo.save(c);
    }

    @Override
    public List<Complaint> getComplaintsForUser(User user) {
        return repo.findByCustomer(user);
    }

    @Override
    public List<Complaint> getPrioritizedComplaints() {
        return repo.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }
}

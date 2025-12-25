package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;

import java.util.List;

public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository repo;

    public PriorityRuleServiceImpl(PriorityRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public int computePriorityScore(Complaint complaint) {
        int score = 0;

        if (complaint.getSeverity() != null)
            score += complaint.getSeverity().ordinal() + 1;

        if (complaint.getUrgency() != null)
            score += complaint.getUrgency().ordinal() + 1;

        List<PriorityRule> rules = repo.findByActiveTrue();
        for (PriorityRule rule : rules) {
            score += rule.getWeight();
            complaint.getPriorityRules().add(rule);
        }
        return score;
    }

    @Override
    public List<PriorityRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}

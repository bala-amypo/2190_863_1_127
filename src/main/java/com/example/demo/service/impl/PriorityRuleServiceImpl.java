package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;

import java.util.List;

public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository repository;

    public PriorityRuleServiceImpl(PriorityRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public int computePriorityScore(Complaint complaint) {
        int score = 0;

        if (complaint.getSeverity() != null) score += complaint.getSeverity().ordinal();
        if (complaint.getUrgency() != null) score += complaint.getUrgency().ordinal();

        List<PriorityRule> rules = repository.findByActiveTrue();
        for (PriorityRule rule : rules) {
            score += rule.getWeight();
        }
        return score;
    }

    @Override
    public List<PriorityRule> getActiveRules() {
        return repository.findByActiveTrue();
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository repo;

    public PriorityRuleServiceImpl(PriorityRuleRepository repo) {
        this.repo = repo;
    }

    public int computePriorityScore(Complaint c) {
        int score = 0;
        if (c.getSeverity() == Complaint.Severity.CRITICAL) score += 50;
        if (c.getUrgency() == Complaint.Urgency.IMMEDIATE) score += 30;
        for (PriorityRule r : repo.findByActiveTrue()) {
            score += r.getWeight();
        }
        return score;
    }

    public List<PriorityRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}

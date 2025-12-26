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

    public int computePriorityScore(Complaint complaint) {
        int score = 0;
        if (complaint.getSeverity() != null) score += complaint.getSeverity().ordinal();
        if (complaint.getUrgency() != null) score += complaint.getUrgency().ordinal();

        for (PriorityRule r : repo.findByActiveTrue()) {
            score += r.getWeight();
        }
        return score;
    }

    public List<PriorityRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}

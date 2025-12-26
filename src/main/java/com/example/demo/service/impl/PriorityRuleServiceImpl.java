package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository priorityRuleRepository;

    @Autowired
    public PriorityRuleServiceImpl(PriorityRuleRepository priorityRuleRepository) {
        this.priorityRuleRepository = priorityRuleRepository;
    }

    @Override
    public int computePriorityScore(Complaint complaint) {
        List<PriorityRule> activeRules = priorityRuleRepository.findByActiveTrue();
        
        int baseScore = 0;
        
        // Calculate base score from severity
        switch (complaint.getSeverity()) {
            case LOW:
                baseScore += 1;
                break;
            case MEDIUM:
                baseScore += 3;
                break;
            case HIGH:
                baseScore += 5;
                break;
            case CRITICAL:
                baseScore += 10;
                break;
        }
        
        // Add urgency score
        switch (complaint.getUrgency()) {
            case LOW:
                baseScore += 1;
                break;
            case MEDIUM:
                baseScore += 3;
                break;
            case HIGH:
                baseScore += 5;
                break;
            case IMMEDIATE:
                baseScore += 10;
                break;
        }
        
        // Apply rule weights
        int ruleBonus = 0;
        for (PriorityRule rule : activeRules) {
            ruleBonus += rule.getWeight();
        }
        
        return baseScore + ruleBonus;
    }

    @Override
    public List<PriorityRule> getActiveRules() {
        return priorityRuleRepository.findByActiveTrue();
    }
}
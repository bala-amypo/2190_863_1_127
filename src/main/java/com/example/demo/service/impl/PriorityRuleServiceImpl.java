package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PriorityRuleServiceImpl implements PriorityRuleService {
    
    private final PriorityRuleRepository priorityRuleRepository;
    
    public PriorityRuleServiceImpl(PriorityRuleRepository priorityRuleRepository) {
        this.priorityRuleRepository = priorityRuleRepository;
    }
    
    @Override
    public int computePriorityScore(Complaint complaint) {
        int score = 0;
        
        // Base score from severity
        switch (complaint.getSeverity()) {
            case LOW:
                score += 10;
                break;
            case MEDIUM:
                score += 25;
                break;
            case HIGH:
                score += 50;
                break;
            case CRITICAL:
                score += 100;
                break;
        }
        
        // Add score from urgency
        switch (complaint.getUrgency()) {
            case LOW:
                score += 5;
                break;
            case MEDIUM:
                score += 15;
                break;
            case HIGH:
                score += 30;
                break;
            case IMMEDIATE:
                score += 50;
                break;
        }
        
        // Apply active priority rules
        List<PriorityRule> activeRules = getActiveRules();
        for (PriorityRule rule : activeRules) {
            score += rule.getWeight();
        }
        
        return Math.max(0, score);
    }
    
    @Override
    public List<PriorityRule> getActiveRules() {
        return priorityRuleRepository.findByActiveTrue();
    }
    
    @Override
    public PriorityRule createRule(String ruleName, String description, Integer weight) {
        PriorityRule rule = new PriorityRule(ruleName, description, weight);
        return priorityRuleRepository.save(rule);
    }
}

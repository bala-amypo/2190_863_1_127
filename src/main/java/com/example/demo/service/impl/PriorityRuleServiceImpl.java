package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityRuleServiceImpl implements PriorityRuleService {
    private final PriorityRuleRepository priorityRuleRepository;
    
    public PriorityRuleServiceImpl(PriorityRuleRepository priorityRuleRepository) {
        this.priorityRuleRepository = priorityRuleRepository;
    }
    
    @Override
    public int computePriorityScore(Complaint complaint) {
        List<PriorityRule> activeRules = priorityRuleRepository.findByActiveTrue();
        int score = 0;
        
        for (PriorityRule rule : activeRules) {
            score += rule.getWeight();
        }
        
        // Add base score based on severity and urgency
        if (complaint.getSeverity() != null) {
            switch (complaint.getSeverity()) {
                case CRITICAL: score += 10; break;
                case HIGH: score += 7; break;
                case MEDIUM: score += 4; break;
                case LOW: score += 1; break;
            }
        }
        
        if (complaint.getUrgency() != null) {
            switch (complaint.getUrgency()) {
                case IMMEDIATE: score += 8; break;
                case HIGH: score += 5; break;
                case MEDIUM: score += 3; break;
                case LOW: score += 1; break;
            }
        }
        
        return Math.max(0, score);
    }
    
    @Override
    public List<PriorityRule> getActiveRules() {
        return priorityRuleRepository.findByActiveTrue();
    }
}
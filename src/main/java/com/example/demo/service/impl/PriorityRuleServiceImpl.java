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
        int baseScore = getSeverityScore(complaint.getSeverity()) + getUrgencyScore(complaint.getUrgency());
        
        int totalWeight = activeRules.stream().mapToInt(PriorityRule::getWeight).sum();
        return baseScore + totalWeight;
    }
    
    @Override
    public List<PriorityRule> getActiveRules() {
        return priorityRuleRepository.findByActiveTrue();
    }
    
    private int getSeverityScore(Complaint.Severity severity) {
        switch (severity) {
            case CRITICAL: return 4;
            case HIGH: return 3;
            case MEDIUM: return 2;
            case LOW: return 1;
            default: return 0;
        }
    }
    
    private int getUrgencyScore(Complaint.Urgency urgency) {
        switch (urgency) {
            case IMMEDIATE: return 4;
            case HIGH: return 3;
            case MEDIUM: return 2;
            case LOW: return 1;
            default: return 0;
        }
    }
}
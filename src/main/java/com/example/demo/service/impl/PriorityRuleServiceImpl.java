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
    public List<PriorityRule> getActiveRules() {
        return priorityRuleRepository.findByActiveTrue();
    }

    @Override
    public int computePriorityScore(Complaint complaint) {
        int score = 0;
        List<PriorityRule> rules = getActiveRules();
        for (PriorityRule r : rules) {
            // For simplicity, just add weight if active
            score += r.getWeight();
        }

        // Optional: boost score by severity/urgency
        if (complaint.getSeverity() != null) {
            switch (complaint.getSeverity()) {
                case LOW -> score += 1;
                case MEDIUM -> score += 3;
                case HIGH -> score += 5;
                case CRITICAL -> score += 10;
            }
        }
        if (complaint.getUrgency() != null) {
            switch (complaint.getUrgency()) {
                case LOW -> score += 1;
                case MEDIUM -> score += 3;
                case HIGH -> score += 5;
                case IMMEDIATE -> score += 10;
            }
        }

        return score;
    }
}

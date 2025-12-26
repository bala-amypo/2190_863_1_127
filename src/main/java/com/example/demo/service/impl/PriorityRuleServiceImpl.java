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

        int score = 0;

        // base score from severity
        if (complaint.getSeverity() != null) {
            switch (complaint.getSeverity()) {
                case CRITICAL -> score += 10;
                case HIGH -> score += 7;
                case MEDIUM -> score += 4;
                case LOW -> score += 1;
            }
        }

        // base score from urgency
        if (complaint.getUrgency() != null) {
            switch (complaint.getUrgency()) {
                case IMMEDIATE -> score += 10;
                case HIGH -> score += 6;
                case MEDIUM -> score += 3;
                case LOW -> score += 1;
            }
        }

        // add rule weights
        List<PriorityRule> rules = priorityRuleRepository.findByActiveTrue();
        if (rules != null) {
            for (PriorityRule rule : rules) {
                score += rule.getWeight();
                complaint.getPriorityRules().add(rule);
            }
        }

        return score;
    }

    @Override
    public List<PriorityRule> getActiveRules() {
        return priorityRuleRepository.findByActiveTrue();
    }
}

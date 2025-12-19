package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository ruleRepository;

    @Override
    public int computePriorityScore(Complaint complaint) {
        List<PriorityRule> rules = ruleRepository.findByActiveTrue();
        int score = 0;
        for (PriorityRule rule : rules) {
            score += rule.getWeight();
        }
        // Include severity and urgency influence (example: HIGH=3, MEDIUM=2, LOW=1)
        score += complaint.getSeverity().ordinal() + complaint.getUrgency().ordinal();
        return Math.max(score, 0);
    }

    @Override
    public List<PriorityRule> getActiveRules() {
        return ruleRepository.findByActiveTrue();
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;

import java.util.List;

public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository priorityRuleRepository;

    public PriorityRuleServiceImpl(PriorityRuleRepository repo){
        this.priorityRuleRepository = repo;
    }

    @Override
    public int computePriorityScore(Complaint complaint) {
        int score = 0;
        List<PriorityRule> rules = priorityRuleRepository.findByActiveTrue();
        for(PriorityRule rule : rules){
            score += rule.getWeight();
        }
        return score;
    }

    @Override
    public List<PriorityRule> getActiveRules() {
        return priorityRuleRepository.findByActiveTrue();
    }
}

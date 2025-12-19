package com.example.demo.service.impl;

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
    public PriorityRule createPriorityRule(PriorityRule priorityRule) {
        return priorityRuleRepository.save(priorityRule);
    }

    @Override
    public List<PriorityRule> getAllPriorityRules() {
        return priorityRuleRepository.findAll();
    }

    @Override
    public PriorityRule getPriorityRuleById(Long id) {
        return priorityRuleRepository.findById(id).orElse(null);
    }
}

package com.example.demo.service;

import com.example.demo.entity.PriorityRule;
import java.util.List;

public interface PriorityRuleService {
    PriorityRule createPriorityRule(PriorityRule priorityRule);
    List<PriorityRule> getAllPriorityRules();
    PriorityRule getPriorityRuleById(Long id);
}

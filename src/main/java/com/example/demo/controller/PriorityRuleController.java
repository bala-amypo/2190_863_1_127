package com.example.demo.controller;

import com.example.demo.entity.PriorityRule;
import com.example.demo.service.PriorityRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rules")
public class PriorityRuleController {
    
    private final PriorityRuleService priorityRuleService;
    
    public PriorityRuleController(PriorityRuleService priorityRuleService) {
        this.priorityRuleService = priorityRuleService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<PriorityRule>> getAllActiveRules() {
        List<PriorityRule> rules = priorityRuleService.getActiveRules();
        return ResponseEntity.ok(rules);
    }
    
    @PostMapping("/create")
    public ResponseEntity<PriorityRule> createRule(@RequestBody Map<String, Object> request) {
        String ruleName = (String) request.get("ruleName");
        String description = (String) request.get("description");
        Integer weight = (Integer) request.get("weight");
        
        PriorityRule rule = priorityRuleService.createRule(ruleName, description, weight);
        return ResponseEntity.ok(rule);
    }
}

package com.example.demo.controller;

import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/priority-rules")
public class PriorityRuleController {

    @Autowired
    private PriorityRuleRepository priorityRuleRepository;

    @Autowired
    private PriorityRuleService priorityRuleService;

    @GetMapping
    public ResponseEntity<List<PriorityRule>> getAllRules() {
        List<PriorityRule> rules = priorityRuleRepository.findAll();
        return ResponseEntity.ok(rules);
    }

    @GetMapping("/active")
    public ResponseEntity<List<PriorityRule>> getActiveRules() {
        List<PriorityRule> activeRules = priorityRuleService.getActiveRules();
        return ResponseEntity.ok(activeRules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriorityRule> getRuleById(@PathVariable Long id) {
        return priorityRuleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PriorityRule> createRule(@RequestBody PriorityRule rule) {
        PriorityRule savedRule = priorityRuleRepository.save(rule);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriorityRule> updateRule(
            @PathVariable Long id,
            @RequestBody PriorityRule ruleDetails) {
        
        return priorityRuleRepository.findById(id)
                .map(rule -> {
                    rule.setRuleName(ruleDetails.getRuleName());
                    rule.setDescription(ruleDetails.getDescription());
                    rule.setWeight(ruleDetails.getWeight());
                    rule.setActive(ruleDetails.isActive());
                    PriorityRule updatedRule = priorityRuleRepository.save(rule);
                    return ResponseEntity.ok(updatedRule);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<PriorityRule> toggleRuleStatus(@PathVariable Long id) {
        return priorityRuleRepository.findById(id)
                .map(rule -> {
                    rule.setActive(!rule.isActive());
                    PriorityRule updatedRule = priorityRuleRepository.save(rule);
                    return ResponseEntity.ok(updatedRule);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        if (priorityRuleRepository.existsById(id)) {
            priorityRuleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
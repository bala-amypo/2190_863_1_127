// PriorityRuleController.java
package com.example.demo.controller;

import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class PriorityRuleController {

    private final PriorityRuleRepository repository;

    public PriorityRuleController(PriorityRuleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<PriorityRule> getAllRules() {
        return repository.findAll();
    }

    @PostMapping
    public PriorityRule createRule(@RequestBody PriorityRule rule) {
        return repository.save(rule);
    }
}

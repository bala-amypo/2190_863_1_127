package com.example.demo.controller;

import com.example.demo.entity.PriorityRule;
import com.example.demo.service.PriorityRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class PriorityRuleController {

    private final PriorityRuleService priorityRuleService;

    @Autowired
    public PriorityRuleController(PriorityRuleService priorityRuleService) {
        this.priorityRuleService = priorityRuleService;
    }

    @GetMapping("/all")
    public List<PriorityRule> getAllRules() {
        return priorityRuleService.getActiveRules();
    }
}

package com.example.demo.controller;

import com.example.demo.entity.PriorityRule;
import com.example.demo.service.PriorityRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/priority-rules")
public class PriorityRuleController {
    
    @Autowired
    private PriorityRuleService priorityRuleService;
    
    @GetMapping("/active")
    public List<PriorityRule> getActiveRules() {
        return priorityRuleService.getActiveRules();
    }
}
package com.example.demo.controller;

import com.example.demo.entity.PriorityRule;
import com.example.demo.service.PriorityRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class PriorityRuleController {

    private final PriorityRuleService service;

    public PriorityRuleController(PriorityRuleService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<PriorityRule> getAllRules() {
        return service.getActiveRules();
    }
}

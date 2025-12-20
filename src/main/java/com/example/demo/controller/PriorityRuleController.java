package com.example.demo.controller;

import com.example.demo.entity.PriorityRule;
import com.example.demo.service.PriorityRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
@RequiredArgsConstructor
public class PriorityRuleController {

    private final PriorityRuleService priorityRuleService;

    @GetMapping("/all")
    public ResponseEntity<List<PriorityRule>> getAllRules() {
        return ResponseEntity.ok(priorityRuleService.getActiveRules());
    }
}

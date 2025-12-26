package com.example.demo.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.demo.entity.PriorityRule;
import com.example.demo.service.PriorityRuleService;


@RestController
@RequestMapping("/rules")

public class PriorityRuleController{
    @Autowired
    PriorityRuleService service;

    @GetMapping("/all")
    public List<PriorityRule> getAllRules(){
        return service.getAllRules();
    }
}

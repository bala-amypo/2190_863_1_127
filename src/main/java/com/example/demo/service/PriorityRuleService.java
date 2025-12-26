package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;

public interface PriorityRuleService {

    // ===== EXISTING METHOD (kept) =====
    List<PriorityRule> getAllRules();

    // ===== TEST-REQUIRED METHODS (added) =====
    List<PriorityRule> getActiveRules();

    int computePriorityScore(Complaint complaint);
}

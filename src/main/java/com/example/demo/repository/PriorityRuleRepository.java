package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.example.demo.entity.PriorityRule;

@Repository
public interface PriorityRuleRepository extends JpaRepository<PriorityRule, Long> {

    // ===== Test-required method =====
    List<PriorityRule> findByActiveTrue();
}

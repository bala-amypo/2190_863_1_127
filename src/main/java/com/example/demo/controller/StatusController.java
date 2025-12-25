package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    
    @GetMapping("/history/{complaintId}")
    public ResponseEntity<List<String>> getStatusHistory(@PathVariable Long complaintId) {
        return ResponseEntity.ok(List.of("NEW", "OPEN", "IN_PROGRESS"));
    }
}
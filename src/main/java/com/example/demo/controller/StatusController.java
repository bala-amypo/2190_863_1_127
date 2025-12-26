package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping("/history/{complaintId}")
    public List<String> getHistory(@PathVariable Long complaintId) {
        return List.of();
    }
}

package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping("/history/{complaintId}")
    public String getHistory(@PathVariable Long complaintId) {
        // For simplicity, returning a placeholder string
        return "History for complaint " + complaintId;
    }
}

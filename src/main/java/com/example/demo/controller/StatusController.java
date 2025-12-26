package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/statuses")
    public Complaint.Status[] getStatuses() {
        return Complaint.Status.values();
    }
}

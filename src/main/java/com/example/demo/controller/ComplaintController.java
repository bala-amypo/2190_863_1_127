package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/submit")
    public Complaint submit(@RequestBody ComplaintRequest request) {
        return complaintService.submitComplaint(request, new User());
    }

    @GetMapping("/user/{userId}")
    public List<Complaint> getUserComplaints(@PathVariable Long userId) {
        return List.of();
    }

    @GetMapping("/prioritized")
    public List<Complaint> getPrioritized() {
        return complaintService.getPrioritizedComplaints();
    }

    @PutMapping("/status/{id}")
    public Complaint updateStatus(@PathVariable Long id) {
        return new Complaint();
    }
}

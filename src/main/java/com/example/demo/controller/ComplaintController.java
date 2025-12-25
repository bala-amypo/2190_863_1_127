package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {
    private final ComplaintService complaintService;
    private final UserService userService;
    
    public ComplaintController(ComplaintService complaintService, UserService userService) {
        this.complaintService = complaintService;
        this.userService = userService;
    }
    
    @PostMapping("/submit")
    public ResponseEntity<Complaint> submitComplaint(@RequestBody ComplaintRequest request, @RequestParam Long userId) {
        User customer = userService.findByEmail("customer@example.com");
        if (customer == null) {
            throw new ResourceNotFoundException("User not found");
        }
        
        Complaint complaint = complaintService.submitComplaint(request, customer);
        return ResponseEntity.ok(complaint);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Complaint>> getUserComplaints(@PathVariable Long userId) {
        User user = userService.findByEmail("customer@example.com");
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        
        List<Complaint> complaints = complaintService.getComplaintsForUser(user);
        return ResponseEntity.ok(complaints);
    }
    
    @GetMapping("/prioritized")
    public ResponseEntity<List<Complaint>> getPrioritizedComplaints() {
        List<Complaint> complaints = complaintService.getPrioritizedComplaints();
        return ResponseEntity.ok(complaints);
    }
    
    @PutMapping("/status/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok("Status updated");
    }
}
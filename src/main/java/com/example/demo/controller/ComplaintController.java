package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.dto.ComplaintResponse;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {
    
    @Autowired
    private ComplaintService complaintService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    public Complaint submitComplaint(@RequestBody ComplaintRequest request, @RequestParam String userEmail) {
        User user = userService.findByEmail(userEmail);
        return complaintService.submitComplaint(request, user);
    }
    
    @GetMapping("/user/{email}")
    public List<Complaint> getUserComplaints(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return complaintService.getComplaintsForUser(user);
    }
    
    @GetMapping("/prioritized")
    public List<Complaint> getPrioritizedComplaints() {
        return complaintService.getPrioritizedComplaints();
    }
}
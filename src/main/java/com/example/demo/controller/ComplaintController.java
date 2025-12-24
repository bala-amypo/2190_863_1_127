package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.dto.ComplaintResponse;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<ComplaintResponse> submitComplaint(@RequestBody ComplaintRequest request) {
        User customer = userService.findById(request.getUserId());
        Complaint complaint = complaintService.submitComplaint(request, customer);
        
        ComplaintResponse response = new ComplaintResponse(complaint);
        response.setSuccess(true);
        response.setMessage("Complaint submitted successfully");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ComplaintResponse>> getUserComplaints(@PathVariable Long userId) {
        User user = userService.findById(userId);
        List<Complaint> complaints = complaintService.getComplaintsForUser(user);
        
        List<ComplaintResponse> responses = complaints.stream()
                .map(ComplaintResponse::new)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/prioritized")
    public ResponseEntity<List<ComplaintResponse>> getPrioritizedComplaints() {
        List<Complaint> complaints = complaintService.getPrioritizedComplaints();
        
        List<ComplaintResponse> responses = complaints.stream()
                .map(ComplaintResponse::new)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/status/{id}")
    public ResponseEntity<Map<String, Object>> updateComplaintStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        
        Complaint.Status status = Complaint.Status.valueOf(request.get("status"));
        Complaint complaint = complaintService.updateComplaintStatus(id, status);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Complaint status updated successfully");
        response.put("complaint", new ComplaintResponse(complaint));
        
        return ResponseEntity.ok(response);
    }
}

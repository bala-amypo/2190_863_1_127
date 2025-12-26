package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.dto.ComplaintResponse;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ComplaintResponse> submitComplaint(
            @RequestBody ComplaintRequest request,
            Authentication authentication) {
        
        User customer = userService.findByEmail(authentication.getName());
        Complaint complaint = complaintService.submitComplaint(request, customer);
        
        return ResponseEntity.ok(mapToResponse(complaint));
    }

    @GetMapping("/my-complaints")
    public ResponseEntity<List<ComplaintResponse>> getMyComplaints(Authentication authentication) {
        User customer = userService.findByEmail(authentication.getName());
        List<Complaint> complaints = complaintService.getComplaintsForUser(customer);
        
        return ResponseEntity.ok(
            complaints.stream().map(this::mapToResponse).collect(Collectors.toList())
        );
    }

    @GetMapping("/prioritized")
    public ResponseEntity<List<ComplaintResponse>> getPrioritizedComplaints() {
        List<Complaint> complaints = complaintService.getPrioritizedComplaints();
        
        return ResponseEntity.ok(
            complaints.stream().map(this::mapToResponse).collect(Collectors.toList())
        );
    }

    private ComplaintResponse mapToResponse(Complaint complaint) {
        ComplaintResponse response = new ComplaintResponse();
        response.setId(complaint.getId());
        response.setTitle(complaint.getTitle());
        response.setDescription(complaint.getDescription());
        response.setCategory(complaint.getCategory());
        response.setChannel(complaint.getChannel());
        response.setSeverity(complaint.getSeverity());
        response.setUrgency(complaint.getUrgency());
        response.setStatus(complaint.getStatus());
        response.setPriorityScore(complaint.getPriorityScore());
        response.setCustomerEmail(complaint.getCustomer().getEmail());
        if (complaint.getAssignedAgent() != null) {
            response.setAssignedAgentEmail(complaint.getAssignedAgent().getEmail());
        }
        response.setCreatedAt(complaint.getCreatedAt());
        response.setUpdatedAt(complaint.getUpdatedAt());
        response.setResolvedAt(complaint.getResolvedAt());
        return response;
    }
}
package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public ComplaintController(ComplaintService complaintService,
                               UserService userService,
                               JwtUtil jwtUtil) {
        this.complaintService = complaintService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // ✅ SUBMIT COMPLAINT (FIXES 500 ERROR)
    @PostMapping("/submit")
    public Complaint submitComplaint(
            @RequestBody ComplaintRequest request,
            @RequestHeader("Authorization") String authHeader
    ) {
        // Extract token from header
        String token = authHeader.substring(7);

        // Extract email from token
        String email = jwtUtil.extractEmail(token);

        // Load logged-in user
        User customer = userService.findByEmail(email);

        // Submit complaint with customer set
        return complaintService.submitComplaint(request, customer);
    }

    // ✅ GET COMPLAINTS FOR LOGGED-IN USER
    @GetMapping("/my")
    public List<Complaint> getMyComplaints(
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        User user = userService.findByEmail(email);

        return complaintService.getComplaintsForUser(user);
    }

    // ✅ GET ALL PRIORITIZED COMPLAINTS (AGENT / ADMIN)
    @GetMapping("/prioritized")
    public List<Complaint> getPrioritizedComplaints() {
        return complaintService.getPrioritizedComplaints();
    }
}

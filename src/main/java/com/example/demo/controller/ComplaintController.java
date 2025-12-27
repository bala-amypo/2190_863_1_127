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

    // ==================================================
    // POST /complaints/submit
    // ==================================================
    @PostMapping("/submit")
    public Complaint submitComplaint(
            @RequestBody ComplaintRequest request,
            @RequestHeader("Authorization") String authHeader
    ) {
        // Remove Bearer + possible quotes (Swagger safe)
        String token = authHeader.substring(7).replace("\"", "");

        // Extract logged-in user
        String email = jwtUtil.extractEmail(token);
        User customer = userService.findByEmail(email);

        // Submit complaint
        return complaintService.submitComplaint(request, customer);
    }

    // ==================================================
    // GET /complaints/user/{userId}
    // ==================================================
    @GetMapping("/user/{userId}")
    public List<Complaint> getUserComplaints(@PathVariable Long userId) {

        // IMPORTANT: load managed entity (not new User())
        User user = userService.findById(userId);

        return complaintService.getComplaintsForUser(user);
    }

    // ==================================================
    // GET /complaints/prioritized
    // ==================================================
    @GetMapping("/prioritized")
    public List<Complaint> getPrioritizedComplaints() {
        return complaintService.getPrioritizedComplaints();
    }

    // ==================================================
    // PUT /complaints/status/{id}
    // ==================================================
    @PutMapping("/status/{id}")
    public Complaint updateStatus(@PathVariable Long id,
                                  @RequestParam Complaint.Status status) {

        // Fetch complaint
        Complaint complaint = complaintService.getById(id);

        // Update status
        complaint.setStatus(status);

        // Save and return
        return complaintService.save(complaint);
    }
}

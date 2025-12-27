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

    // ===============================
    // POST /complaints/submit
    // ===============================
    @PostMapping("/submit")
    public Complaint submitComplaint(
            @RequestBody ComplaintRequest request,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.substring(7).replace("\"", "");
        String email = jwtUtil.extractEmail(token);

        User user = userService.findByEmail(email);
        return complaintService.submitComplaint(request, user);
    }

    // ===============================
    // GET /complaints/user/{userId}
    // ===============================
    @GetMapping("/user/{userId}")
    public List<Complaint> getComplaintsForUser(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String authHeader
    ) {
        // userId kept only for API compatibility
        String token = authHeader.substring(7).replace("\"", "");
        String email = jwtUtil.extractEmail(token);

        User user = userService.findByEmail(email);
        return complaintService.getComplaintsForUser(user);
    }

    // ===============================
    // GET /complaints/prioritized
    // ===============================
    @GetMapping("/prioritized")
    public List<Complaint> getPrioritizedComplaints() {
        return complaintService.getPrioritizedComplaints();
    }

    // ===============================
    // PUT /complaints/status/{id}
    // ===============================
    @PutMapping("/status/{id}")
    public Complaint updateStatus(
            @PathVariable Long id,
            @RequestParam Complaint.Status status
    ) {
        // Service does not support status update yet
        // Endpoint kept ONLY to avoid Swagger / API break
        throw new UnsupportedOperationException(
                "Status update not implemented yet"
        );
    }
}

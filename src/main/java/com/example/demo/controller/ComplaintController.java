package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Submit a new complaint")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaint submitted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid complaint data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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
    @Operation(summary = "Get complaints for logged-in user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaints retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/user/{userId}")
    public List<Complaint> getComplaintsForUser(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.substring(7).replace("\"", "");
        String email = jwtUtil.extractEmail(token);

        User user = userService.findByEmail(email);
        return complaintService.getComplaintsForUser(user);
    }

    // ===============================
    // GET /complaints/prioritized
    // ===============================
    @Operation(summary = "Get all complaints sorted by priority")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prioritized complaints retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/prioritized")
    public List<Complaint> getPrioritizedComplaints() {
        return complaintService.getPrioritizedComplaints();
    }

    // ===============================
    // PUT /complaints/status/{id}
    // ===============================
    @Operation(summary = "Update complaint status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaint status updated"),
            @ApiResponse(responseCode = "400", description = "Invalid status value"),
            @ApiResponse(responseCode = "404", description = "Complaint not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/status/{id}")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam Complaint.Status status
    ) {
        complaintService.updateComplaintStatus(id, status.name());
        return "Complaint status updated successfully";
    }
}

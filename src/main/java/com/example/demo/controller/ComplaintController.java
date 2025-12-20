package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.dto.ComplaintResponse;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserService userService;

    @PostMapping("/submit")
    public ResponseEntity<ComplaintResponse> submitComplaint(@RequestBody ComplaintRequest request, @RequestParam Long userId) {
        User user = userService.findByEmail(userId.toString());
        Complaint complaint = complaintService.submitComplaint(request, user);
        return ResponseEntity.ok(new ComplaintResponse(complaint.getId(), complaint.getTitle(), complaint.getStatus(), complaint.getPriorityScore()));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ComplaintResponse>> getUserComplaints(@PathVariable Long userId) {
        User user = userService.findByEmail(userId.toString());
        List<ComplaintResponse> responses = complaintService.getComplaintsForUser(user)
                .stream()
                .map(c -> new ComplaintResponse(c.getId(), c.getTitle(), c.getStatus(), c.getPriorityScore()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/prioritized")
    public ResponseEntity<List<ComplaintResponse>> getPrioritizedComplaints() {
        List<ComplaintResponse> responses = complaintService.getPrioritizedComplaints()
                .stream()
                .map(c -> new ComplaintResponse(c.getId(), c.getTitle(), c.getStatus(), c.getPriorityScore()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}

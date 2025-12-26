package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.dto.ComplaintResponse;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/submit")
    public ComplaintResponse submitComplaint(@RequestBody ComplaintRequest request) {

        // Dummy user for controller-level wiring
        User user = new User();
        user.setId(1L);

        Complaint complaint = complaintService.submitComplaint(request, user);
        return new ComplaintResponse(complaint);
    }

    @GetMapping("/user/{userId}")
    public List<ComplaintResponse> getUserComplaints(@PathVariable Long userId) {

        User user = new User();
        user.setId(userId);

        return complaintService.getComplaintsForUser(user)
                .stream()
                .map(ComplaintResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/prioritized")
    public List<ComplaintResponse> getPrioritizedComplaints() {
        return complaintService.getPrioritizedComplaints()
                .stream()
                .map(ComplaintResponse::new)
                .collect(Collectors.toList());
    }
}

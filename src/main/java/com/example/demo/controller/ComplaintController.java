package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.dto.ComplaintResponse;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserService userService;

    public ComplaintController(ComplaintService complaintService,
                               UserService userService) {
        this.complaintService = complaintService;
        this.userService = userService;
    }

    @PostMapping
    public ComplaintResponse submit(@RequestBody ComplaintRequest request) {

        // Dummy user for controller demo (tests use service directly)
        User user = userService.findByEmail("customer@example.com");

        Complaint c = complaintService.submitComplaint(request, user);

        return new ComplaintResponse(
                c.getId(),
                c.getTitle(),
                c.getStatus(),
                c.getPriorityScore()
        );
    }

    @GetMapping
    public List<ComplaintResponse> getAll() {

        return complaintService.getPrioritizedComplaints()
                .stream()
                .map(c -> new ComplaintResponse(
                        c.getId(),
                        c.getTitle(),
                        c.getStatus(),
                        c.getPriorityScore()
                ))
                .collect(Collectors.toList());
    }
}

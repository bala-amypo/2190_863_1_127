package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/submit")
    public Complaint submitComplaint(@RequestBody ComplaintRequest request, @RequestParam Long userId) {
        User customer = new User();
        customer.setId(userId);
        return complaintService.submitComplaint(request, customer);
    }

    @GetMapping("/user/{userId}")
    public List<Complaint> getUserComplaints(@PathVariable Long userId) {
        User customer = new User();
        customer.setId(userId);
        return complaintService.getComplaintsForUser(customer);
    }

    @GetMapping("/prioritized")
    public List<Complaint> getPrioritizedComplaints() {
        return complaintService.getPrioritizedComplaints();
    }

    @PutMapping("/status/{id}")
    public Complaint updateStatus(@PathVariable Long id, @RequestParam Complaint.Status status) {
        Complaint c = complaintService.getPrioritizedComplaints()
                .stream().filter(comp -> comp.getId().equals(id))
                .findFirst().orElseThrow();
        c.setStatus(status);
        return c;
    }
}

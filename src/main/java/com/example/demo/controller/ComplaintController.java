package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import com.example.demo.service.ComplaintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }


    @PostMapping
    public Complaint submit(@RequestBody Complaint complaint) {
        return service.submitComplaint(complaint);
    }


    @GetMapping
    public List<Complaint> getAll() {
        return service.getAllComplaints();
    }


    @GetMapping("/prioritized")
    public List<Complaint> prioritized() {
        return service.getPrioritizedComplaints();
    }

  
    @PutMapping("/{id}/status")
    public Complaint updateComplaintStatus(
            @PathVariable Long id,
            @RequestParam Complaint.Status status) {

        Complaint complaint = service.getComplaintById(id);
        complaint.setStatus(status);
        return service.updateComplaint(complaint);
    }
}

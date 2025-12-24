package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplaintRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final ComplaintRepository complaintRepository;

    public StatusController(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

   
    @GetMapping("/{id}")
    public Map<String, Object> getStatus(@PathVariable Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("complaintId", complaint.getId());
        response.put("status", complaint.getStatus().name());

        return response;
    }
}

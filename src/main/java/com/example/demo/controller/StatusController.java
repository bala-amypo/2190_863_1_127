package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import com.example.demo.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusController {

    private final ComplaintRepository complaintRepository;

    // Get the current status of a complaint by ID
    @GetMapping("/{complaintId}")
    public ResponseEntity<String> getStatus(@PathVariable Long complaintId) {
        Optional<Complaint> complaintOpt = complaintRepository.findById(complaintId);
        if (complaintOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(complaintOpt.get().getStatus().name());
    }

    // Update the status of a complaint by ID
    @PutMapping("/{complaintId}")
    public ResponseEntity<String> updateStatus(@PathVariable Long complaintId, @RequestParam Complaint.Status status) {
        Optional<Complaint> complaintOpt = complaintRepository.findById(complaintId);
        if (complaintOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Complaint complaint = complaintOpt.get();
        complaint.setStatus(status);
        complaintRepository.save(complaint);
        return ResponseEntity.ok("Status updated to " + status.name());
    }
}

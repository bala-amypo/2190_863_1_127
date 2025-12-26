package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import com.example.demo.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private ComplaintRepository complaintRepository;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("service", "Complaint Prioritization Engine");
        return ResponseEntity.ok(health);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        long totalComplaints = complaintRepository.count();
        long newComplaints = complaintRepository.findAll().stream()
                .filter(c -> c.getStatus() == Complaint.Status.NEW)
                .count();
        long inProgressComplaints = complaintRepository.findAll().stream()
                .filter(c -> c.getStatus() == Complaint.Status.IN_PROGRESS)
                .count();
        long resolvedComplaints = complaintRepository.findAll().stream()
                .filter(c -> c.getStatus() == Complaint.Status.RESOLVED)
                .count();
        long closedComplaints = complaintRepository.findAll().stream()
                .filter(c -> c.getStatus() == Complaint.Status.CLOSED)
                .count();

        stats.put("totalComplaints", totalComplaints);
        stats.put("newComplaints", newComplaints);
        stats.put("inProgressComplaints", inProgressComplaints);
        stats.put("resolvedComplaints", resolvedComplaints);
        stats.put("closedComplaints", closedComplaints);
        stats.put("timestamp", LocalDateTime.now());

        return ResponseEntity.ok(stats);
    }

    @PatchMapping("/complaints/{id}/status")
    public ResponseEntity<Complaint> updateComplaintStatus(
            @PathVariable Long id,
            @RequestParam Complaint.Status status) {
        
        return complaintRepository.findById(id)
                .map(complaint -> {
                    complaint.setStatus(status);
                    complaint.setUpdatedAt(LocalDateTime.now());
                    
                    if (status == Complaint.Status.RESOLVED || status == Complaint.Status.CLOSED) {
                        complaint.setResolvedAt(LocalDateTime.now());
                    }
                    
                    Complaint updatedComplaint = complaintRepository.save(complaint);
                    return ResponseEntity.ok(updatedComplaint);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/complaints/by-severity")
    public ResponseEntity<Map<String, Long>> getComplaintsBySeverity() {
        Map<String, Long> severityStats = new HashMap<>();
        
        for (Complaint.Severity severity : Complaint.Severity.values()) {
            long count = complaintRepository.findAll().stream()
                    .filter(c -> c.getSeverity() == severity)
                    .count();
            severityStats.put(severity.name(), count);
        }
        
        return ResponseEntity.ok(severityStats);
    }

    @GetMapping("/complaints/by-urgency")
    public ResponseEntity<Map<String, Long>> getComplaintsByUrgency() {
        Map<String, Long> urgencyStats = new HashMap<>();
        
        for (Complaint.Urgency urgency : Complaint.Urgency.values()) {
            long count = complaintRepository.findAll().stream()
                    .filter(c -> c.getUrgency() == urgency)
                    .count();
            urgencyStats.put(urgency.name(), count);
        }
        
        return ResponseEntity.ok(urgencyStats);
    }
}
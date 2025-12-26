package com.example.demo.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.demo.entity.Complaint;
import com.example.demo.service.ComplaintService;

@RestController
@RequestMapping("/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController{

    @Autowired
    ComplaintService service;

    @PostMapping("/submit")
    public Complaint submitComplaint(@RequestBody Complaint complaint){
        return service.submitComplaint(complaint);
    }

    @GetMapping("/user/{userId}")
    public List<Complaint> getUserComplaints(@PathVariable Long userId){
        return service.getUserComplaints(userId);
    }

    @GetMapping("/prioritized")
    public List<Complaint> getPrioritizedComplaints(){
        return service.getPrioritizedComplaints();
    }

    @PutMapping("/status/{id}")
    public void updateComplaintStatus(@PathVariable Long id,@RequestParam String status){
        service.updateComplaintStatus(id, status);
    }
}

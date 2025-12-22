package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService service;
    private final UserService userService;

    public ComplaintController(ComplaintService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/submit/{email}")
    public Complaint submit(@PathVariable String email,
                            @RequestBody Complaint complaint) {
        User user = userService.findByEmail(email);
        complaint.setCustomer(user);
        return service.submitComplaint(complaint);
    }

    @GetMapping("/user/{email}")
    public List<Complaint> byUser(@PathVariable String email) {
        return service.getComplaintsForUser(userService.findByEmail(email));
    }

    @GetMapping("/prioritized")
    public List<Complaint> prioritized() {
        return service.getPrioritizedComplaints();
    }

    @PutMapping("/{id}/status")
    public Complaint updateComplaintStatus(@PathVariable Long id,@RequestParam Complaint.Status status) {

    Complaint complaint = service.getComplaintById(id);
    complaint.setStatus(status);
    return service.updateComplaint(complaint);
}

}

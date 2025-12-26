package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔥 ADD THIS
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final PriorityRuleService priorityRuleService;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                Object o1,
                                Object o2,
                                PriorityRuleService priorityRuleService) {
        this.complaintRepository = complaintRepository;
        this.priorityRuleService = priorityRuleService;
    }

    @Override
    public Complaint submitComplaint(ComplaintRequest req, User user) {
        Complaint complaint = new Complaint();
        complaint.setTitle(req.getTitle());
        complaint.setDescription(req.getDescription());
        complaint.setCategory(req.getCategory());
        complaint.setChannel(req.getChannel());
        complaint.setSeverity(req.getSeverity());
        complaint.setUrgency(req.getUrgency());
        complaint.setCustomer(user);

        int score = priorityRuleService.computePriorityScore(complaint);
        complaint.setPriorityScore(score);

        return complaintRepository.save(complaint);
    }

    @Override
    public List<Complaint> getComplaintsForUser(User user) {
        return complaintRepository.findByCustomer(user);
    }

    @Override
    public List<Complaint> getPrioritizedComplaints() {
        return complaintRepository.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }
}

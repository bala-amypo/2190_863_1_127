package com.example.demo.repository;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import java.util.List;

public interface ComplaintRepository {
    List<Complaint> findByCustomer(User customer);
    List<Complaint> findAllOrderByPriorityScoreDescCreatedAtAsc();
    Complaint save(Complaint complaint);
}
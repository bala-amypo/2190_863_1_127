package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.demo.entity.ComplaintStatus;

@Repository
public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Long> {
    List<ComplaintStatus> findByComplaintId(Long complaintId);
    
}

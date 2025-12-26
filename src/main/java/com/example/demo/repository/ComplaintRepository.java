package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // For tests
    List<Complaint> findByCustomer(User customer);

    // For controllers (runtime)
    List<Complaint> findByCustomer_Id(Long userId);

    @Query("""
        SELECT c
        FROM Complaint c
        ORDER BY c.priorityScore DESC, c.submittedOn ASC
    """)
    List<Complaint> findAllOrderByPriorityScoreDescCreatedAtAsc();
}

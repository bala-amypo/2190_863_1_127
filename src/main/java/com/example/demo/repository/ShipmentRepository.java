package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Shipment;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment,Long>{
    Optional<User>findByEmail(String email);
}
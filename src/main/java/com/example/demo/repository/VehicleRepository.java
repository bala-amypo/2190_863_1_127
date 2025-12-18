package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Vehicle;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Long>{
    List<Vehicle>findByUserId(Long userId);
}
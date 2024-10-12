package com.example.Atlas.Repository;

import com.example.Atlas.Entity.wtEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface wtRepository extends JpaRepository<wtEntity, Integer> {
    
     List<wtEntity> findByDepartmentId(int departmentId);

     @Query("SELECT s FROM wtEntity s WHERE s.department.id = :departmentId AND s.sorted = 0") 
    List<wtEntity> findByDepartmentIdAndSorted(int departmentId); 
}

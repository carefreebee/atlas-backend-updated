package com.example.Atlas.Repository;

import com.example.Atlas.Entity.soEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface soRepository extends JpaRepository<soEntity, Integer>{

    List<soEntity> findByDepartmentId(int departmentId);
    
    @Query("SELECT s FROM soEntity s WHERE s.department.id = :departmentId AND s.sorted = 0") 
    List<soEntity> findByDepartmentIdAndSorted(int departmentId); 
}

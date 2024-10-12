package com.example.Atlas.Repository;

import com.example.Atlas.Entity.woEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface woRepository extends JpaRepository<woEntity, Integer>{
    
    List<woEntity> findByDepartmentId(int departmentId);

    @Query("SELECT s FROM woEntity s WHERE s.department.id = :departmentId AND s.sorted = 0") 
    List<woEntity> findByDepartmentIdAndSorted(int departmentId); 
}

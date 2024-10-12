package com.example.Atlas.Repository;

import com.example.Atlas.Entity.stEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface stRepository extends JpaRepository<stEntity, Integer>{

    List<stEntity> findByDepartmentId(int departmentId);

    @Query("SELECT s FROM stEntity s WHERE s.department.id = :departmentId AND s.sorted = 0") 
    List<stEntity> findByDepartmentIdAndSorted(int departmentId); 
    
}

package com.example.Atlas.Repository;

import com.example.Atlas.Entity.LearningEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningRepository extends JpaRepository<LearningEntity, Integer>{

    List<LearningEntity> findByDepartmentId(int departmentId);
  
    LearningEntity findTopByOrderByIdDesc();
}
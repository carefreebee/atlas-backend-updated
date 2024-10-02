package com.example.Atlas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
         
}

package com.example.Atlas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.DepartmentEntity;


public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

    Optional<DepartmentEntity> findById(int id); 
         
}

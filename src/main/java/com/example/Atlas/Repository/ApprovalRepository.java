package com.example.Atlas.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.ApprovalEntity;


public interface  ApprovalRepository extends JpaRepository<ApprovalEntity, Integer>{
     List<ApprovalEntity> findByDepartmentId(int departmentId);
}

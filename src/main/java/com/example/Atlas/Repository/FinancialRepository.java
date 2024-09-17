package com.example.Atlas.Repository;

import com.example.Atlas.Entity.FinancialEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialRepository extends JpaRepository<FinancialEntity, Integer> {

    List<FinancialEntity> findByDepartmentId(int departmentId);

    FinancialEntity findTopByOrderByIdDesc();
}
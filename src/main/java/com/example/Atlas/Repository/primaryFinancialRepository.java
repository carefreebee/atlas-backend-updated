package com.example.Atlas.Repository;

import com.example.Atlas.Entity.primaryFinancialEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface primaryFinancialRepository extends JpaRepository<primaryFinancialEntity, Integer> {

    List<primaryFinancialEntity> findByDepartmentId(int departmentId);

    primaryFinancialEntity findTopByOrderByIdDesc();

    List<primaryFinancialEntity> findByTargetCode(String targetCode);
}
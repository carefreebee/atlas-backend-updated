package com.example.Atlas.Repository;

import com.example.Atlas.Entity.StakeholderEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StakeholderRepository extends JpaRepository<StakeholderEntity, Integer> {

    List<StakeholderEntity> findByDepartmentId(int departmentId);

    StakeholderEntity findTopByOrderByIdDesc();
}
package com.example.Atlas.Repository;

import com.example.Atlas.Entity.InternalEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InternalRepository extends JpaRepository<InternalEntity, Integer> {

    List<InternalEntity> findByDepartmentId(int departmentId);

    InternalEntity findTopByOrderByIdDesc();
}

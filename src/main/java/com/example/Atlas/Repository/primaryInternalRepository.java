package com.example.Atlas.Repository;

import com.example.Atlas.Entity.primaryInternalEntity;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface primaryInternalRepository extends JpaRepository<primaryInternalEntity, Integer> {

    List<primaryInternalEntity> findByDepartmentId(int departmentId);
    primaryInternalEntity findTopByOrderByIdDesc();
}

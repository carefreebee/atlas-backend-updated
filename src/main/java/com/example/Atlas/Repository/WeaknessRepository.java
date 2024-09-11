package com.example.Atlas.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Atlas.Entity.WeaknessEntity;
public interface WeaknessRepository extends JpaRepository<WeaknessEntity, Integer>{

    List<WeaknessEntity> findByDepartmentIdAndIsDeleteFalse(int departmentId);
    List<WeaknessEntity> findByDepartmentIdAndIsDeleteTrue(int departmentId);

    @Query("SELECT w.department.department_name, COUNT(w) FROM WeaknessEntity w GROUP BY w.department.department_name")
    List<Object[]> countWeaknessesByDepartment();
}

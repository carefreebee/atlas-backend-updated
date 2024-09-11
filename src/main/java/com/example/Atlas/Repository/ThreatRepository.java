package com.example.Atlas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Atlas.Entity.ThreatEntity;
public interface ThreatRepository extends JpaRepository<ThreatEntity, Integer>  {


     List<ThreatEntity> findByDepartmentIdAndIsDeleteFalse(int departmentId);
     List<ThreatEntity> findByDepartmentIdAndIsDeleteTrue(int departmentId);
    
     @Query("SELECT t.department.department_name, COUNT(t) FROM ThreatEntity t GROUP BY t.department.department_name")
     List<Object[]> countThreatsByDepartment();
    
}

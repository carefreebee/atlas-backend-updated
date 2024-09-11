package com.example.Atlas.Repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Atlas.Entity.StrengthEntity;

public interface StrengthRepository extends JpaRepository<StrengthEntity, Integer> {

    List<StrengthEntity> findByDepartmentIdAndIsDeleteFalse(int departmentId);
    List<StrengthEntity> findByDepartmentIdAndIsDeleteTrue(int departmentId);

     @Query("SELECT d.department_name, COUNT(s) FROM StrengthEntity s JOIN s.department d GROUP BY d.department_name")
    List<Object[]> countStrengthsByDepartment();
    
}

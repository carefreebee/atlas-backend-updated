package com.example.Atlas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Atlas.Entity.OpportunityEntity;



public interface OpportunityRepository extends JpaRepository<OpportunityEntity, Integer> {

     List<OpportunityEntity> findByDepartmentIdAndIsDeleteFalse(int departmentId);
     List<OpportunityEntity> findByDepartmentIdAndIsDeleteTrue(int departmentId);

     @Query("SELECT o.department.department_name, COUNT(o) FROM OpportunityEntity o GROUP BY o.department.department_name")
     List<Object[]> countOpportunitiesByDepartment();
}


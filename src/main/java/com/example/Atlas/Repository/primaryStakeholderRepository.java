package com.example.Atlas.Repository;

import com.example.Atlas.Entity.primaryStakeholderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface primaryStakeholderRepository extends JpaRepository<primaryStakeholderEntity, Integer> {

    List<primaryStakeholderEntity> findByDepartmentId(int departmentId);

    primaryStakeholderEntity findTopByOrderByIdDesc();
    
    List<primaryStakeholderEntity> findByTargetCode(String targetCode);
    
}

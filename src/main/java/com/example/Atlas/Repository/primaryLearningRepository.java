package com.example.Atlas.Repository;

import com.example.Atlas.Entity.primaryLearningEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface primaryLearningRepository extends JpaRepository<primaryLearningEntity, Integer> {

    List<primaryLearningEntity> findByDepartmentId(int departmentId);
    primaryLearningEntity findTopByOrderByIdDesc();
    
}

package com.example.Atlas.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.Atlas.Entity.GoalEntity;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {

    List<GoalEntity> findAllByDepartmentId(int departmentId);

    Optional<GoalEntity> findTopByDepartmentIdOrderByIdDesc(int departmentId);

    Optional<GoalEntity> findTopByDepartmentIdAndAccomplishedFalseOrderByIdDesc(int departmentId);

    Optional<GoalEntity> findTopByOrderByIdDesc();

    Optional<GoalEntity> findFirstByAccomplishedOrderByIdDesc(boolean accomplished);

    List<GoalEntity> findAllByDepartmentIdAndAccomplished(int departmentId, boolean accomplished);

    @Modifying
    @Query("UPDATE GoalEntity g SET g.accomplished = :accomplished WHERE g.id = :id")
    void updateAccomplishedStatus(@Param("id") Integer id, @Param("accomplished") boolean accomplished);

}

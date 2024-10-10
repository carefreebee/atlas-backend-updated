package com.example.Atlas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    <Optional> UserEntity findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    // Count the number of users in a specific department
    int countByDepartment(DepartmentEntity department);

    List<UserEntity> findByDepartment(DepartmentEntity department);
}
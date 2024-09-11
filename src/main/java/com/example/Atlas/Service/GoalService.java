package com.example.Atlas.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.GoalEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.GoalRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoalService {

        @Autowired
        GoalRepository goalRepository;

        @Autowired
        DepartmentRepository departmentRepository;

        public List<GoalEntity> getAllGoalsByDepartmentId(int departmentId) {
                DepartmentEntity department = departmentRepository.findById(departmentId)
                                .orElseThrow(() -> new NoSuchElementException("Department not found"));
                return goalRepository.findAllByDepartmentId(department.getId());
        }

        public GoalEntity getLatestGoalsByDepartmentId(int departmentId) {
                DepartmentEntity department = departmentRepository.findById(departmentId)
                                .orElseThrow(() -> new NoSuchElementException("Department not found"));
                return goalRepository.findTopByDepartmentIdOrderByIdDesc(department.getId())
                                .orElseThrow(() -> new NoSuchElementException(
                                                "No goals found for department id " + departmentId));
        }

        public GoalEntity saveGoal(GoalEntity goal) {
                return goalRepository.save(goal);
            }

            public List<GoalEntity> getAllGoals() {
                return goalRepository.findAll();
            }

        @Transactional(readOnly = true)
        public GoalEntity getLatestGoal() {
                return goalRepository.findFirstByAccomplishedOrderByIdDesc(false)
                                .orElseThrow(() -> new NoSuchElementException("No goals found"));
        }

        public GoalEntity insertGoal(GoalEntity request) {
                DepartmentEntity department = departmentRepository.findById(request.getDepartment().getId())
                                .orElseThrow(() -> new NoSuchElementException("Department not found"));
                request.setDepartment(department);
                return goalRepository.save(request); // ID will be auto-generated
        }

        public GoalEntity getGoalById(int goalId) {
                return goalRepository.findById(goalId)
                                .orElseThrow(() -> new NoSuchElementException("Goal not found with id " + goalId));
        }

        public GoalEntity updateGoalById(int goalId, GoalEntity request) {
                GoalEntity existingGoal = goalRepository.findById(goalId)
                                .orElseThrow(() -> new NoSuchElementException("Goal not found with id " + goalId));
                DepartmentEntity department = departmentRepository.findById(request.getDepartment().getId())
                                .orElseThrow(() -> new NoSuchElementException("Department not found with id " + request.getDepartment().getId()));

                existingGoal.setVision(request.getVision());
                existingGoal.setProposition(request.getProposition());
                existingGoal.setMission(request.getMission());
                existingGoal.setGoals(request.getGoals()); 
                existingGoal.setTargetYear(request.getTargetYear());
                existingGoal.setDepartment(department);

                return goalRepository.save(existingGoal); // Save and return the updated goal
        }

        @Transactional
        public void updateAccomplishedStatus(Integer id, boolean accomplished) {
                GoalEntity goal = goalRepository.findById(id)
                                .orElseThrow(() -> new NoSuchElementException("Goal not found with id " + id));
                goal.setAccomplished(accomplished);
                goalRepository.save(goal); // Ensure this save operation persists the update
        }

        public List<GoalEntity> getAccomplishedGoalsByDepartmentId(int departmentId) {
                DepartmentEntity department = departmentRepository.findById(departmentId)
                                .orElseThrow(() -> new NoSuchElementException("Department not found"));
                return goalRepository.findAllByDepartmentIdAndAccomplished(department.getId(), true);
        }
}
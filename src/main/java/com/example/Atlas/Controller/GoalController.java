package com.example.Atlas.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Atlas.Entity.GoalEntity;
import com.example.Atlas.Service.GoalService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/goals")
@CrossOrigin
public class GoalController {

    @Autowired
    GoalService goalService;

    @PostMapping("/insert")
    public ResponseEntity<GoalEntity> insertGoal(@RequestBody GoalEntity request) {
        try {
            GoalEntity insertedGoal = goalService.insertGoal(request);
            return new ResponseEntity<>(insertedGoal, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<GoalEntity> addGoal(@RequestBody GoalEntity goal) {
        GoalEntity savedGoal = goalService.saveGoal(goal);
        return ResponseEntity.ok(savedGoal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalEntity> getGoal(@PathVariable int goalId) {
        GoalEntity goal = goalService.getGoalById(goalId);
        return ResponseEntity.ok(goal);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GoalEntity>> getAllGoals() {
        List<GoalEntity> goals = goalService.getAllGoals();
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/get/department/{departmentId}")
    public ResponseEntity<?> getLatestGoalsByDepartmentId(@PathVariable int departmentId) {
        try {
            GoalEntity latestGoals = goalService.getLatestGoalsByDepartmentId(departmentId);
            return ResponseEntity.ok(latestGoals);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No goals found for department id " + departmentId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get/goal/{goalId}")
    public ResponseEntity<?> getLatestGoalsByGoalId(@PathVariable int goalId) {
        try {
            GoalEntity goal = goalService.getGoalById(goalId);
            return ResponseEntity.ok(goal);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No goal found with id " + goalId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestGoal() {
        try {
            GoalEntity goal = goalService.getLatestGoal();
            return ResponseEntity.ok(goal);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No goals found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{goalId}")
    public ResponseEntity<?> updateGoalById(@PathVariable int goalId, @RequestBody GoalEntity request) {
        try {
            GoalEntity updatedGoal = goalService.updateGoalById(goalId, request);
            return ResponseEntity.ok(updatedGoal);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateGoalStatus(@PathVariable Integer id, @RequestParam boolean accomplished) {
        try {
            goalService.updateAccomplishedStatus(id, accomplished);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAccomplished/{departmentId}")
    public ResponseEntity<?> getAccomplishedGoalsByDepartmentId(@PathVariable int departmentId) {
        try {
            List<GoalEntity> goals = goalService.getAccomplishedGoalsByDepartmentId(departmentId);
            return ResponseEntity.ok(goals);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No accomplished goals found for department id " + departmentId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getGoal/{goalId}")
    public ResponseEntity<?> getGoalById(@PathVariable int goalId) {
        try {
            GoalEntity goal = goalService.getGoalById(goalId);
            return ResponseEntity.ok(goal);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No goal found with id " + goalId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getAll/{departmentId}")
    public ResponseEntity<?> getAllGoalsByDepartmentId(@PathVariable int departmentId) {
        try {
            List<GoalEntity> goals = goalService.getAllGoalsByDepartmentId(departmentId);
            return ResponseEntity.ok(goals);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No goals found for department id " + departmentId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

}
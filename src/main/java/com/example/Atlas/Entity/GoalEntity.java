package com.example.Atlas.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goal")
public class GoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String vision;

    private String proposition;

    private String mission;

    @ElementCollection // This annotation maps a collection of elements as a separate table.
    @CollectionTable(name = "goal_goals", joinColumns = @JoinColumn(name = "goal_id"))
    @Column(name = "goal")

    private List<String> goals; // List to store multiple goals

    private String targetYear;

    private boolean accomplished;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id") // Specify the foreign key column
    private DepartmentEntity department;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getProposition() {
        return proposition;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getMission() {
        return mission;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public boolean isAccomplished() {
        return accomplished;
    }

    public void setAccomplished(boolean accomplished) {
        this.accomplished = accomplished;
    }

    public List<String> getGoals() {
        return goals;
    }

    public void setGoals(List<String> goals) {
        this.goals = goals;
    }

    public String getTargetYear() {
        return targetYear;
    }

    public void setTargetYear(String targetYear) {
        this.targetYear = targetYear;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}
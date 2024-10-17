package com.example.Atlas.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String department_name;

    private String head_officer;

    private String department_landline;

    private String location;

    private String university;

    private String description;

    private String email;

    private String category;

    @Column(columnDefinition = "tinyint DEFAULT '0'")
    private int hasPrimaryStrats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getHead_officer() {
        return head_officer;
    }

    public void setHead_officer(String head_officer) {
        this.head_officer = head_officer;
    }

    public String getDepartment_landline() {
        return department_landline;
    }

    public void setDepartment_landline(String department_landline) {
        this.department_landline = department_landline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHasPrimaryStrats() {
        return hasPrimaryStrats;
    }

    public void setHasPrimaryStrats(int hasPrimaryStrats) {
        this.hasPrimaryStrats = hasPrimaryStrats;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

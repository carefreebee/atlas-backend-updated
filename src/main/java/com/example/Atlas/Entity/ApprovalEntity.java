package com.example.Atlas.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "approval")
public class ApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String preparedByName;

    private String preparedByRole;

    private String acknowledgedByName;

    private String acknowledgedByRole;

    private String reviewedByName;

    private String reviewedByRole;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id") // Specify the foreign key column
    private DepartmentEntity department;

    public String getPreparedByName() {
        return preparedByName;
    }

    public void setPreparedByName(String preparedByName) {
        this.preparedByName = preparedByName;
    }

    public String getPreparedByRole() {
        return preparedByRole;
    }

    public void setPreparedByRole(String preparedByRole) {
        this.preparedByRole = preparedByRole;
    }

    public String getAcknowledgedByName() {
        return acknowledgedByName;
    }

    public void setAcknowledgedByName(String acknowledgedByName) {
        this.acknowledgedByName = acknowledgedByName;
    }

    public String getAcknowledgedByRole() {
        return acknowledgedByRole;
    }

    public void setAcknowledgedByRole(String acknowledgedByRole) {
        this.acknowledgedByRole = acknowledgedByRole;
    }

    public String getReviewedByName() {
        return reviewedByName;
    }

    public void setReviewedByName(String reviewedByName) {
        this.reviewedByName = reviewedByName;
    }

    public String getReviewedByRole() {
        return reviewedByRole;
    }

    public void setReviewedByRole(String reviewedByRole) {
        this.reviewedByRole = reviewedByRole;
    }

    // Getters and setters for department
    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    
}

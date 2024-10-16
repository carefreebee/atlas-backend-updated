package com.example.Atlas.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "internal_bsc")
public class InternalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String target_code;

    private String office_target;

    private String metric;

    private String status;

    private String key_performance_indicator;

    private Float target_performance;

    private Float actual_performance;

    private String semester;

    private String actions;

    private Float budget;

    private String incharge;

    private String ofi;

    private String evidence_link;

    private String targetYear;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @Column(columnDefinition = "tinyint DEFAULT '0'")
    private int user_generated;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarget_code() {
        return target_code;
    }

    public void setTarget_code(String target_code) {
        this.target_code = target_code;
    }

    public String getOffice_target() {
        return office_target;
    }

    public void setOffice_target(String office_target) {
        this.office_target = office_target;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey_performance_indicator() {
        return key_performance_indicator;
    }

    public void setKey_performance_indicator(String key_performance_indicator) {
        this.key_performance_indicator = key_performance_indicator;
    }

    public Float getTarget_performance() {
        return target_performance;
    }

    public void setTarget_performance(Float target_performance) {
        this.target_performance = target_performance;
    }

    public Float getActual_performance() {
        return actual_performance;
    }

    public void setActual_performance(Float actual_performance) {
        this.actual_performance = actual_performance;
    }

    // Getters and setters for department
    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    public String getIncharge() {
        return incharge;
    }

    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    public String getOfi() {
        return ofi;
    }

    public void setOfi(String ofi) {
        this.ofi = ofi;
    }

    public int getUser_generated() {
        return user_generated;
    }

    public void setUser_generated(int user_generated) {
        this.user_generated = user_generated;
    }

    public String getEvidence_link() {
        return evidence_link;

    }
    public void setEvidence_link(String evidence_link) {
        this.evidence_link = evidence_link;

    }

    public String getTargetYear() {
        return targetYear;
    }

    public void setTargetYear(String targetYear) {
        this.targetYear = targetYear;
    }

}

package com.example.Atlas.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "primary_learning_bsc")
public class primaryLearningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String targetCode;
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

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    // getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarget_code() {
        return targetCode;
    }

    public void setTarget_code(String targetCode) {
        this.targetCode = targetCode;
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

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

}

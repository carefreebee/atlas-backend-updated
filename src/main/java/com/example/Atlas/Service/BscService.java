package com.example.Atlas.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.FinancialEntity;
import com.example.Atlas.Entity.InternalEntity;
import com.example.Atlas.Entity.StakeholderEntity;
import com.example.Atlas.Entity.primaryFinancialEntity;
import com.example.Atlas.Entity.primaryInternalEntity;
import com.example.Atlas.Entity.primaryLearningEntity;
import com.example.Atlas.Entity.primaryStakeholderEntity;
import com.example.Atlas.Entity.LearningEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.FinancialRepository;
import com.example.Atlas.Repository.LearningRepository;
import com.example.Atlas.Repository.StakeholderRepository;
import com.example.Atlas.Repository.primaryFinancialRepository;
import com.example.Atlas.Repository.primaryInternalRepository;
import com.example.Atlas.Repository.primaryLearningRepository;
import com.example.Atlas.Repository.primaryStakeholderRepository;
import com.example.Atlas.Repository.InternalRepository;

@Service
public class BscService {
    @Autowired
    FinancialRepository financialrepo;

    @Autowired
    StakeholderRepository stakeholderrepo;

    @Autowired
    LearningRepository learningrepo;

    @Autowired
    InternalRepository internalrepo;

    @Autowired
    primaryFinancialRepository primaryFinancialrepo;

    @Autowired
    primaryStakeholderRepository primaryStakeholderrepo;

    @Autowired
    primaryLearningRepository primaryLearningrepo;

    @Autowired
    primaryInternalRepository primaryInternalrepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public FinancialEntity insertFinancialBsc(FinancialEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return financialrepo.save(request);
    }

    public List<FinancialEntity> findFinancialByDepartmentId(int departmentId) {
        return financialrepo.findByDepartmentId(departmentId);
    }

    public Integer getLatestFinancialId() {
        FinancialEntity latestFinancial = financialrepo.findTopByOrderByIdDesc();
        return latestFinancial != null ? latestFinancial.getId() : null;
    }

    public FinancialEntity updateFinancialBscById(int id, FinancialEntity request) {
        Optional<FinancialEntity> optionalFinancial = financialrepo.findById(id);
        if (optionalFinancial.isPresent()) {
            FinancialEntity existingFinancial = optionalFinancial.get();
            // Update fields based on request
            if (request.getTarget_code() != null)
                existingFinancial.setTarget_code(request.getTarget_code());
            if (request.getOffice_target() != null)
                existingFinancial.setOffice_target(request.getOffice_target());
            if (request.getMetric() != null)
                existingFinancial.setMetric(request.getMetric());
            if (request.getStatus() != null)
                existingFinancial.setStatus(request.getStatus());
            if (request.getKey_performance_indicator() != null)
                existingFinancial.setKey_performance_indicator(request.getKey_performance_indicator());
            if (request.getTarget_performance() != null)
                existingFinancial.setTarget_performance(request.getTarget_performance());
            if (request.getActual_performance() != null)
                existingFinancial.setActual_performance(request.getActual_performance());
            if (request.getBudget() != null)
                existingFinancial.setBudget(request.getBudget());
            if (request.getIncharge() != null)
                existingFinancial.setIncharge(request.getIncharge());
            if (request.getOfi() != null)
                existingFinancial.setOfi(request.getOfi());
            if(request.getTargetYear() != null) 
                existingFinancial.setTargetYear(request.getTargetYear());
            if (request.getActions() != null)
                existingFinancial.setActions(request.getActions());
            if (request.getEvidence_link() != null) //link
                existingFinancial.setEvidence_link(request.getEvidence_link());
            // Department may not be updated
            return financialrepo.save(existingFinancial);
        } else {
            throw new NoSuchElementException("Financial scorecard not found with ID: " + id);
        }
    }

    // Stakeholder
    public StakeholderEntity insertStakeholderBsc(StakeholderEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return stakeholderrepo.save(request);
    }

    public List<StakeholderEntity> findStakeholderByDepartmentId(int departmentId) {
        return stakeholderrepo.findByDepartmentId(departmentId);
    }

    public Integer getLatestStakeholderId() {
        StakeholderEntity latestStakeholder = stakeholderrepo.findTopByOrderByIdDesc();
        return latestStakeholder != null ? latestStakeholder.getId() : null;
    }

    public StakeholderEntity updateStakeholderBscById(int id, StakeholderEntity request) {
        Optional<StakeholderEntity> optionalStakeholder = stakeholderrepo.findById(request.getId());
        if (optionalStakeholder.isPresent()) {
            StakeholderEntity existingStakeholder = optionalStakeholder.get();
            if (request.getTarget_code() != null)
                existingStakeholder.setTarget_code(request.getTarget_code());
            if (request.getOffice_target() != null)
                existingStakeholder.setOffice_target(request.getOffice_target());
            if (request.getMetric() != null)
                existingStakeholder.setMetric(request.getMetric());
            if (request.getStatus() != null)
                existingStakeholder.setStatus(request.getStatus());
            if (request.getKey_performance_indicator() != null)
                existingStakeholder.setKey_performance_indicator(request.getKey_performance_indicator());
            if (request.getTarget_performance() != null)
                existingStakeholder.setTarget_performance(request.getTarget_performance());
            if (request.getActual_performance() != null)
                existingStakeholder.setActual_performance(request.getActual_performance());
            if (request.getSemester() != null)
                existingStakeholder.setSemester(request.getSemester());
            if (request.getActions() != null)
                existingStakeholder.setActions(request.getActions());
            if (request.getBudget() != null)
                existingStakeholder.setBudget(request.getBudget());
            if (request.getIncharge() != null)
                existingStakeholder.setIncharge(request.getIncharge());
            if (request.getOfi() != null)
                existingStakeholder.setOfi(request.getOfi());
            if(request.getTargetYear() != null) 
                existingStakeholder.setTargetYear(request.getTargetYear());
            if (request.getEvidence_link() != null) //link
                existingStakeholder.setEvidence_link(request.getEvidence_link()); 
            return stakeholderrepo.save(existingStakeholder);
        } else {
            throw new NoSuchElementException("Stakeholder scorecard not found with ID: " + request.getId());
        }
    }

    // Learning
    public LearningEntity insertLearningBsc(LearningEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return learningrepo.save(request);
    }

    public List<LearningEntity> findLearningByDepartmentId(int departmentId) {
        return learningrepo.findByDepartmentId(departmentId);
    }

    public Integer getLatestLearningId() {
        LearningEntity latestLearning = learningrepo.findTopByOrderByIdDesc();
        return latestLearning != null ? latestLearning.getId() : null;
    }

    public LearningEntity updateLearningBscById(int id, LearningEntity request) {
        Optional<LearningEntity> optionalLearning = learningrepo.findById(request.getId());
        if (optionalLearning.isPresent()) {
            LearningEntity existingStakeholder = optionalLearning.get();
            if (request.getTarget_code() != null)
                existingStakeholder.setTarget_code(request.getTarget_code());
            if (request.getOffice_target() != null)
                existingStakeholder.setOffice_target(request.getOffice_target());
            if (request.getMetric() != null)
                existingStakeholder.setMetric(request.getMetric());
            if (request.getStatus() != null)
                existingStakeholder.setStatus(request.getStatus());
            if (request.getKey_performance_indicator() != null)
                existingStakeholder.setKey_performance_indicator(request.getKey_performance_indicator());
            if (request.getTarget_performance() != null)
                existingStakeholder.setTarget_performance(request.getTarget_performance());
            if (request.getActual_performance() != null)
                existingStakeholder.setActual_performance(request.getActual_performance());
            if (request.getSemester() != null)
                existingStakeholder.setSemester(request.getSemester());
            if (request.getActions() != null)
                existingStakeholder.setActions(request.getActions());
            if (request.getBudget() != null)
                existingStakeholder.setBudget(request.getBudget());
            if (request.getIncharge() != null)
                existingStakeholder.setIncharge(request.getIncharge());
            if (request.getOfi() != null)
                existingStakeholder.setOfi(request.getOfi());
            if(request.getTargetYear() != null) 
                existingStakeholder.setTargetYear(request.getTargetYear());
            if (request.getEvidence_link() != null) //link
                existingStakeholder.setEvidence_link(request.getEvidence_link()); 
            return learningrepo.save(existingStakeholder);
        } else {
            throw new NoSuchElementException("Learning scorecard not found with ID: " + request.getId());
        }
    }

    // Internal
    public InternalEntity insertInternalBsc(InternalEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return internalrepo.save(request);
    }

    public List<InternalEntity> findInternalByDepartmentId(int departmentId) {
        return internalrepo.findByDepartmentId(departmentId);
    }

    public Integer getLatestInternalId() {
        InternalEntity latestInternal = internalrepo.findTopByOrderByIdDesc();
        return latestInternal != null ? latestInternal.getId() : null;
    }

    public InternalEntity updateInternalBscById(int id, InternalEntity request) {
        Optional<InternalEntity> optionInternal = internalrepo.findById(request.getId());
        if (optionInternal.isPresent()) {
            InternalEntity existingStakeholder = optionInternal.get();
            if (request.getTarget_code() != null)
                existingStakeholder.setTarget_code(request.getTarget_code());
            if (request.getOffice_target() != null)
                existingStakeholder.setOffice_target(request.getOffice_target());
            if (request.getMetric() != null)
                existingStakeholder.setMetric(request.getMetric());
            if (request.getStatus() != null)
                existingStakeholder.setStatus(request.getStatus());
            if (request.getKey_performance_indicator() != null)
                existingStakeholder.setKey_performance_indicator(request.getKey_performance_indicator());
            if (request.getTarget_performance() != null)
                existingStakeholder.setTarget_performance(request.getTarget_performance());
            if (request.getActual_performance() != null)
                existingStakeholder.setActual_performance(request.getActual_performance());
            if (request.getSemester() != null)
                existingStakeholder.setSemester(request.getSemester());
            if (request.getActions() != null)
                existingStakeholder.setActions(request.getActions());
            if (request.getBudget() != null)
                existingStakeholder.setBudget(request.getBudget());
            if (request.getIncharge() != null)
                existingStakeholder.setIncharge(request.getIncharge());
            if (request.getOfi() != null)
                existingStakeholder.setOfi(request.getOfi());
            if(request.getTargetYear() != null) 
                existingStakeholder.setTargetYear(request.getTargetYear());
            if (request.getEvidence_link() != null) //link
                existingStakeholder.setEvidence_link(request.getEvidence_link()); 
            return internalrepo.save(existingStakeholder);
        } else {
            throw new NoSuchElementException("Internal scorecard not found with ID: " + request.getId());
        }
    }

    public int getFinancialCount() {
        return (int) financialrepo.count();
    }

    public int getStakeholderCount() {
        return (int) stakeholderrepo.count();
    }

    public int getLearningCount() {
        return (int) learningrepo.count();
    }

    public int getInternalCount() {
        return (int) internalrepo.count();
    }

    // Primary Functions

    // Primary Financial
    public primaryFinancialEntity insertPrimaryFinancialBsc(primaryFinancialEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return primaryFinancialrepo.save(request);
    }

    public List<primaryFinancialEntity> findPrimaryFinancialByDepartmentId(int departmentId) {
        return primaryFinancialrepo.findByDepartmentId(departmentId);
    }

    public Integer getLatestPrimaryFinancialId() {
        primaryFinancialEntity latestPrimaryFinancial = primaryFinancialrepo.findTopByOrderByIdDesc();
        return latestPrimaryFinancial != null ? latestPrimaryFinancial.getId() : null;
    }

    public primaryFinancialEntity updatePrimaryFinancialBscById(int id, primaryFinancialEntity request) {
        Optional<primaryFinancialEntity> optionalPrimaryFinancial = primaryFinancialrepo.findById(request.getId());
        
        if (optionalPrimaryFinancial.isPresent()) {
            primaryFinancialEntity existingPrimaryFinancial = optionalPrimaryFinancial.get();
            if (request.getTarget_code() != null)
                existingPrimaryFinancial.setTarget_code(request.getTarget_code());
            if (request.getOffice_target() != null)
                existingPrimaryFinancial.setOffice_target(request.getOffice_target());
            if (request.getMetric() != null)
                existingPrimaryFinancial.setMetric(request.getMetric());
            if (request.getStatus() != null)
                existingPrimaryFinancial.setStatus(request.getStatus());
            if (request.getKey_performance_indicator() != null)
                existingPrimaryFinancial.setKey_performance_indicator(request.getKey_performance_indicator());
            if (request.getTarget_performance() != null)
                existingPrimaryFinancial.setTarget_performance(request.getTarget_performance());
            if (request.getActual_performance() != null)
                existingPrimaryFinancial.setActual_performance(request.getActual_performance());
            if (request.getSemester() != null)
                existingPrimaryFinancial.setSemester(request.getSemester());
            if (request.getActions() != null)
                existingPrimaryFinancial.setActions(request.getActions());
            if (request.getBudget() != null)
                existingPrimaryFinancial.setBudget(request.getBudget());
            if (request.getIncharge() != null)
                existingPrimaryFinancial.setIncharge(request.getIncharge());
            if (request.getOfi() != null)
                existingPrimaryFinancial.setOfi(request.getOfi());
            if(request.getTargetYear() != null) 
                existingPrimaryFinancial.setTargetYear(request.getTargetYear());
            if(request.getEvidence_link() != null)
                existingPrimaryFinancial.setEvidence_link(request.getEvidence_link());
            return primaryFinancialrepo.save(existingPrimaryFinancial);
        } else {
            throw new NoSuchElementException("Primary Financial scorecard not found with ID: " + request.getId());
        }
    }

    // Primary Stakeholder
    public primaryStakeholderEntity insertPrimaryStakeholderBsc(primaryStakeholderEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return primaryStakeholderrepo.save(request);
    }

    public List<primaryStakeholderEntity> findPrimaryStakeholderByDepartmentId(int departmentId) {
        return primaryStakeholderrepo.findByDepartmentId(departmentId);
    }

    public Integer getLatestPrimaryStakeholderId() {
        primaryStakeholderEntity latestPrimaryStakeholder = primaryStakeholderrepo.findTopByOrderByIdDesc();
        return latestPrimaryStakeholder != null ? latestPrimaryStakeholder.getId() : null;
    }

    public primaryStakeholderEntity updatePrimaryStakeholderBscById(int id, primaryStakeholderEntity request) {
        Optional<primaryStakeholderEntity> optionalPrimaryStakeholder = primaryStakeholderrepo
                .findById(request.getId());
        if (optionalPrimaryStakeholder.isPresent()) {
            primaryStakeholderEntity existingPrimaryStakeholder = optionalPrimaryStakeholder.get();
            if (request.getTarget_code() != null)
                existingPrimaryStakeholder.setTarget_code(request.getTarget_code());
            if (request.getOffice_target() != null)
                existingPrimaryStakeholder.setOffice_target(request.getOffice_target());
            if (request.getMetric() != null)
                existingPrimaryStakeholder.setMetric(request.getMetric());
            if (request.getStatus() != null)
                existingPrimaryStakeholder.setStatus(request.getStatus());
            if (request.getKey_performance_indicator() != null)
                existingPrimaryStakeholder.setKey_performance_indicator(request.getKey_performance_indicator());
            if (request.getTarget_performance() != null)
                existingPrimaryStakeholder.setTarget_performance(request.getTarget_performance());
            if (request.getActual_performance() != null)
                existingPrimaryStakeholder.setActual_performance(request.getActual_performance());
            if (request.getSemester() != null)
                existingPrimaryStakeholder.setSemester(request.getSemester());
            if (request.getActions() != null)
                existingPrimaryStakeholder.setActions(request.getActions());
            if (request.getBudget() != null)
                existingPrimaryStakeholder.setBudget(request.getBudget());
            if (request.getIncharge() != null)
                existingPrimaryStakeholder.setIncharge(request.getIncharge());
            if (request.getOfi() != null)
                existingPrimaryStakeholder.setOfi(request.getOfi());
            if(request.getTargetYear() != null) 
                existingPrimaryStakeholder.setTargetYear(request.getTargetYear());
            if (request.getEvidence_link() != null)
                existingPrimaryStakeholder.setEvidence_link(request.getEvidence_link());
            return primaryStakeholderrepo.save(existingPrimaryStakeholder);
        } else {
            throw new NoSuchElementException("Primary Stakeholder scorecard not found with ID: " + request.getId());
        }
    }

    // Primary Internal
    public primaryInternalEntity insertPrimaryInternalBsc(primaryInternalEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return primaryInternalrepo.save(request);
    }

    public List<primaryInternalEntity> findPrimaryInternalByDepartmentId(int departmentId) {
        return primaryInternalrepo.findByDepartmentId(departmentId);
    }

    public Integer getLatestPrimaryInternalId() {
        primaryInternalEntity latestPrimaryInternal = primaryInternalrepo.findTopByOrderByIdDesc();
        return latestPrimaryInternal != null ? latestPrimaryInternal.getId() : null;
    }

    public primaryInternalEntity updatePrimaryInternalBscById(int id, primaryInternalEntity request) {
        Optional<primaryInternalEntity> optionalPrimaryInternal = primaryInternalrepo.findById(request.getId());
        if (optionalPrimaryInternal.isPresent()) {
            primaryInternalEntity existingPrimaryInternal = optionalPrimaryInternal.get();
            if (request.getTarget_code() != null)
                existingPrimaryInternal.setTarget_code(request.getTarget_code());
            if (request.getOffice_target() != null)
                existingPrimaryInternal.setOffice_target(request.getOffice_target());
            if (request.getMetric() != null)
                existingPrimaryInternal.setMetric(request.getMetric());
            if (request.getStatus() != null)
                existingPrimaryInternal.setStatus(request.getStatus());
            if (request.getKey_performance_indicator() != null)
                existingPrimaryInternal.setKey_performance_indicator(request.getKey_performance_indicator());
            if (request.getTarget_performance() != null)
                existingPrimaryInternal.setTarget_performance(request.getTarget_performance());
            if (request.getActual_performance() != null)
                existingPrimaryInternal.setActual_performance(request.getActual_performance());
            if (request.getSemester() != null)
                existingPrimaryInternal.setSemester(request.getSemester());
            if (request.getActions() != null)
                existingPrimaryInternal.setActions(request.getActions());
            if (request.getBudget() != null)
                existingPrimaryInternal.setBudget(request.getBudget());
            if (request.getIncharge() != null)
                existingPrimaryInternal.setIncharge(request.getIncharge());
            if (request.getOfi() != null)
                existingPrimaryInternal.setOfi(request.getOfi());
            if(request.getTargetYear() != null) 
                existingPrimaryInternal.setTargetYear(request.getTargetYear());
            if (request.getEvidence_link() != null)
                existingPrimaryInternal.setEvidence_link(request.getEvidence_link());
            return primaryInternalrepo.save(existingPrimaryInternal);
        } else {
            throw new NoSuchElementException("Primary Internal scorecard not found with ID: " + request.getId());
        }
    }

    // Primary Learning
    public primaryLearningEntity insertPrimaryLearningBsc(primaryLearningEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId())
                .orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return primaryLearningrepo.save(request);
    }

    public List<primaryLearningEntity> findPrimaryLearningByDepartmentId(int departmentId) {
        return primaryLearningrepo.findByDepartmentId(departmentId);
    }

    public Integer getLatestPrimaryLearningId() {
        primaryLearningEntity latestPrimaryLearning = primaryLearningrepo.findTopByOrderByIdDesc();
        return latestPrimaryLearning != null ? latestPrimaryLearning.getId() : null;
    }

    public primaryLearningEntity updatePrimaryLearningBscById(int id, primaryLearningEntity request) {
        Optional<primaryLearningEntity> optionalPrimaryLearning = primaryLearningrepo.findById(request.getId());
        if (optionalPrimaryLearning.isPresent()) {
            primaryLearningEntity existingPrimaryLearning = optionalPrimaryLearning.get();
            if (request.getTarget_code() != null)
                existingPrimaryLearning.setTarget_code(request.getTarget_code());
            if (request.getOffice_target() != null)
                existingPrimaryLearning.setOffice_target(request.getOffice_target());
            if (request.getMetric() != null)
                existingPrimaryLearning.setMetric(request.getMetric());
            if (request.getStatus() != null)
                existingPrimaryLearning.setStatus(request.getStatus());
            if (request.getKey_performance_indicator() != null)
                existingPrimaryLearning.setKey_performance_indicator(request.getKey_performance_indicator());
            if (request.getTarget_performance() != null)
                existingPrimaryLearning.setTarget_performance(request.getTarget_performance());
            if (request.getActual_performance() != null)
                existingPrimaryLearning.setActual_performance(request.getActual_performance());
            if (request.getSemester() != null)
                existingPrimaryLearning.setSemester(request.getSemester());
            if (request.getActions() != null)
                existingPrimaryLearning.setActions(request.getActions());
            if (request.getBudget() != null)
                existingPrimaryLearning.setBudget(request.getBudget());
            if (request.getIncharge() != null)
                existingPrimaryLearning.setIncharge(request.getIncharge());
            if (request.getOfi() != null)
                existingPrimaryLearning.setOfi(request.getOfi());
            if(request.getTargetYear() != null) 
                existingPrimaryLearning.setTargetYear(request.getTargetYear());
            if (request.getEvidence_link() != null)
                existingPrimaryLearning.setEvidence_link(request.getEvidence_link());
            return primaryLearningrepo.save(existingPrimaryLearning);
        } else {
            throw new NoSuchElementException("Primary Learning scorecard not found with ID: " + request.getId());
        }
    }


    public List<FinancialEntity> getFinancialTargetYears(int departmentId) { 
        return financialrepo.findByDepartmentId(departmentId); 
    }

    public List<InternalEntity> getInternalTargetYears(int departmentId) { 
        return internalrepo.findByDepartmentId(departmentId); 
    }

    public List<LearningEntity> getLearningTargetYears(int departmentId) { 
        return learningrepo.findByDepartmentId(departmentId); 
    }

    public List<StakeholderEntity> getStakeholderTargetYears(int departmentId) { 
        return stakeholderrepo.findByDepartmentId(departmentId); 
    }
    
    
}
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
import com.example.Atlas.Entity.LearningEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.FinancialRepository;
import com.example.Atlas.Repository.LearningRepository;
import com.example.Atlas.Repository.StakeholderRepository;
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
            if (request.getStartDate() != null)
                existingFinancial.setStartDate(request.getStartDate());
            if (request.getCompletionDate() != null)
                existingFinancial.setCompletionDate(request.getCompletionDate());
            if (request.getStatus() != null)
                existingFinancial.setStatus(request.getStatus());
            if (request.getKey_performance_indicator() != null)
                existingFinancial.setKey_performance_indicator(request.getKey_performance_indicator());
            if (request.getTarget_performance() != null)
                existingFinancial.setTarget_performance(request.getTarget_performance());
            if (request.getActual_performance() != null)
                existingFinancial.setActual_performance(request.getActual_performance());
            if (request.getSemester() != null)
                existingFinancial.setSemester(request.getSemester());
            if (request.getBudget() != null)
                existingFinancial.setBudget(request.getBudget());
            if (request.getIncharge() != null)
                existingFinancial.setIncharge(request.getIncharge());
            if (request.getOfi() != null)
                existingFinancial.setOfi(request.getOfi());
            if (request.getActions() != null)
                existingFinancial.setActions(request.getActions());
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
            if (request.getStartDate() != null)
                existingStakeholder.setStartDate(request.getStartDate());
            if (request.getCompletionDate() != null)
                existingStakeholder.setCompletionDate(request.getCompletionDate());
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
            if (request.getStartDate() != null)
                existingStakeholder.setStartDate(request.getStartDate());
            if (request.getCompletionDate() != null)
                existingStakeholder.setCompletionDate(request.getCompletionDate());
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
            if (request.getStartDate() != null)
                existingStakeholder.setStartDate(request.getStartDate());
            if (request.getCompletionDate() != null)
                existingStakeholder.setCompletionDate(request.getCompletionDate());
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

}

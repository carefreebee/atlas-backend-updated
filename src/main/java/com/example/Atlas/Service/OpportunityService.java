package com.example.Atlas.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Repository.OpportunityRepository;
import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.OpportunityEntity;
import com.example.Atlas.Repository.DepartmentRepository;


@Service
public class OpportunityService {
    @Autowired
    OpportunityRepository opportunityrepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public OpportunityEntity insertOpportunity(OpportunityEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return opportunityrepo.save(request);
    }

    public List<OpportunityEntity> getOpportunityByDepartmentId(int departmentId) {
        return opportunityrepo.findByDepartmentIdAndIsDeleteFalse(departmentId);
    }

     public OpportunityEntity updateOpportunityById(OpportunityEntity request) {
        Optional<OpportunityEntity> optionalOpportunity = opportunityrepo.findById(request.getId());
        if (optionalOpportunity.isPresent()) {
            OpportunityEntity existingOpportunity = optionalOpportunity.get();
            existingOpportunity.setValue(request.getValue());
            existingOpportunity.setDepartment(request.getDepartment());
            return opportunityrepo.save(existingOpportunity);
        } else {
            throw new NoSuchElementException("Opportunity not found with ID: " + request.getId());
        }
    }

    public String deleteOpportunity(int opportunityId) {
        Optional<OpportunityEntity> optionalOpportunity = opportunityrepo.findById(opportunityId);
        if (optionalOpportunity.isPresent()) {
            OpportunityEntity opportunity = optionalOpportunity.get();
            opportunity.setDelete(true);
            opportunityrepo.save(opportunity);
            return "Opportunity " + opportunityId + " is successfully marked as deleted!";
        } else {
            return "Opportunity " + opportunityId + " does not exist!";
        }
    }

    // added
    public List<OpportunityEntity> getDeletedOpportunities(int departmentId) {
        return opportunityrepo.findByDepartmentIdAndIsDeleteTrue(departmentId);
    }

    public OpportunityEntity restoreOpportunity(int opportunityId) {
        Optional<OpportunityEntity> optionalOpportunity = opportunityrepo.findById(opportunityId);
        if (optionalOpportunity.isPresent()) {
            OpportunityEntity opportunity = optionalOpportunity.get();
            opportunity.setDelete(false);
            return opportunityrepo.save(opportunity);
        } else {
            throw new NoSuchElementException("Opportunity not found with ID: " + opportunityId);
        }
    }

    public List<OpportunityEntity> getAllOpportunity() {
        return opportunityrepo.findAll();
    }

    public Map<String, Integer> getOpportunityCountByDepartment() {
        List<Object[]> results = opportunityrepo.countOpportunitiesByDepartment();
        Map<String, Integer> countMap = new HashMap<>();
        for (Object[] result : results) {
            String departmentName = (String) result[0];
            Long count = (Long) result[1];
            countMap.put(departmentName, count.intValue());
        }
        return countMap;
    }

    
}

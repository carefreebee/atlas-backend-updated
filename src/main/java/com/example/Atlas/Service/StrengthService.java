package com.example.Atlas.Service;

import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.StrengthEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.StrengthRepository;

@Service
public class StrengthService {
    @Autowired
    StrengthRepository strengthrepo;
   
     @Autowired
      DepartmentRepository departmentrepo;

       public StrengthEntity insertStrength(StrengthEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return strengthrepo.save(request);
    }

    public List<StrengthEntity> getStrengthsByDepartmentId(int departmentId) {
        return strengthrepo.findByDepartmentIdAndIsDeleteFalse(departmentId);

    }

    
    public StrengthEntity updateStrengthById(StrengthEntity request) {
        Optional<StrengthEntity> optionalStrength = strengthrepo.findById(request.getId());
        if (optionalStrength.isPresent()) {
            StrengthEntity existingStrength = optionalStrength.get();
            existingStrength.setValue(request.getValue());
            existingStrength.setDepartment(request.getDepartment());
            return strengthrepo.save(existingStrength);
        } else {
            throw new NoSuchElementException("Strength not found with ID: " + request.getId());
        }
    }

    public String deleteStrength(int strengthId) {
        Optional<StrengthEntity> optionalStrength = strengthrepo.findById(strengthId);
        if (optionalStrength.isPresent()) {
            StrengthEntity strength = optionalStrength.get();
            strength.setDelete(true);
            strengthrepo.save(strength);
            return "Strength " + strengthId + " is successfully marked as deleted!";
        } else {
            return "Strength " + strengthId + " does not exist!";
        }
    }

    // added
    public List<StrengthEntity> getDeletedStrengths(int departmentId) {
        return strengthrepo.findByDepartmentIdAndIsDeleteTrue(departmentId);
    }

    public StrengthEntity restoreStrength(int strengthId) {
        Optional<StrengthEntity> optionalStrength = strengthrepo.findById(strengthId);
        if (optionalStrength.isPresent()) {
            StrengthEntity strength = optionalStrength.get();
            strength.setDelete(false);
            return strengthrepo.save(strength);
        } else {
            throw new NoSuchElementException("Strength not found with ID: " + strengthId);
        }
    }

    public List<StrengthEntity> getAllStrengths() {
        return strengthrepo.findAll();
    }

    public Map<String, Integer> getStrengthCountByDepartment() {
        List<Object[]> results = strengthrepo.countStrengthsByDepartment();
        Map<String, Integer> countMap = new HashMap<>();
        for (Object[] result : results) {
            String departmentName = (String) result[0];
            Long count = (Long) result[1];
            countMap.put(departmentName, count.intValue());
        }
        return countMap;
    }



}
package com.example.Atlas.Service;

import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.WeaknessRepository;
import com.example.Atlas.Entity.WeaknessEntity;



@Service
public class WeaknessService {
    @Autowired
    WeaknessRepository weaknessrepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public WeaknessEntity insertWeakness(WeaknessEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return weaknessrepo.save(request);
    }
    
     public List<WeaknessEntity> getWeaknessByDepartmentId(int departmentId) {
        return weaknessrepo.findByDepartmentIdAndIsDeleteFalse(departmentId);
    }

    public WeaknessEntity updateWeaknessById(WeaknessEntity request) {
        Optional<WeaknessEntity> optionalWeakness = weaknessrepo.findById(request.getId());
        if (optionalWeakness.isPresent()) {
            WeaknessEntity existingWeakness = optionalWeakness.get();
            existingWeakness.setValue(request.getValue());
            existingWeakness.setDepartment(request.getDepartment());
            return weaknessrepo.save(existingWeakness);
        } else {
            throw new NoSuchElementException("Weakness not found with ID: " + request.getId());
        }
    }


     public String deleteWeakness(int weaknessId) {
        Optional<WeaknessEntity> optionalWeakness = weaknessrepo.findById(weaknessId);
        if (optionalWeakness.isPresent()) {
            WeaknessEntity weakness = optionalWeakness.get();
            weakness.setDelete(true);
            weaknessrepo.save(weakness);
            return "Weakness " + weaknessId + " is successfully marked as deleted!";
        } else {
            return "Weakness " + weaknessId + " does not exist!";
        }
    }

    // added
    public List<WeaknessEntity> getDeletedWeaknesses(int departmentId) {
        return weaknessrepo.findByDepartmentIdAndIsDeleteTrue(departmentId);
    }

    public WeaknessEntity restoreWeakness(int weaknessId) {
        Optional<WeaknessEntity> optionalWeakness = weaknessrepo.findById(weaknessId);
        if (optionalWeakness.isPresent()) {
            WeaknessEntity weakness = optionalWeakness.get();
            weakness.setDelete(false);
            return weaknessrepo.save(weakness);
        } else {
            throw new NoSuchElementException("Weakness not found with ID: " + weaknessId);
        }
    }

    public List<WeaknessEntity> getAllWeakness() {
        return weaknessrepo.findAll();
    }

    public Map<String, Integer> getWeaknessCountByDepartment() {
        List<Object[]> results = weaknessrepo.countWeaknessesByDepartment();
        Map<String, Integer> countMap = new HashMap<>();
        for (Object[] result : results) {
            String departmentName = (String) result[0];
            Long count = (Long) result[1];
            countMap.put(departmentName, count.intValue());
        }
        return countMap;
    }

    
}

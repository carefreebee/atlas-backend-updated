package com.example.Atlas.Service;

import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.ThreatEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.ThreatRepository;

@Service
public class ThreatService {
    @Autowired
    ThreatRepository threatrepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public ThreatEntity insertThreat(ThreatEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return threatrepo.save(request);
    }

    public List<ThreatEntity> getThreatsByDepartmentId(int departmentId) {
        return threatrepo.findByDepartmentIdAndIsDeleteFalse(departmentId);
    }

     public ThreatEntity updateThreatById(ThreatEntity request) {
        Optional<ThreatEntity> optionalThreat = threatrepo.findById(request.getId());
        if (optionalThreat.isPresent()) {
            ThreatEntity existingThreat= optionalThreat.get();
            existingThreat.setValue(request.getValue());
            existingThreat.setDepartment(request.getDepartment());
            return threatrepo.save(existingThreat);
        } else {
            throw new NoSuchElementException("threat not found with ID: " + request.getId());
        }
    }

      public String deleteThreat(int threatId) {
        Optional<ThreatEntity> optionalThreat = threatrepo.findById(threatId);
        if (optionalThreat.isPresent()) {
            ThreatEntity threat = optionalThreat.get();
            threat.setDelete(true);
            threatrepo.save(threat);
            return "Threat " + threatId + " is successfully marked as deleted!";
        } else {
            return "Threat " + threatId + " does not exist!";
        }
    }

    // added
    public List<ThreatEntity> getDeletedThreats(int departmentId) {
        return threatrepo.findByDepartmentIdAndIsDeleteTrue(departmentId);
    }

    public ThreatEntity restoreThreat(int threatId) {
        Optional<ThreatEntity> optionalThreats = threatrepo.findById(threatId);
        if (optionalThreats.isPresent()) {
            ThreatEntity Threats = optionalThreats.get();
            Threats.setDelete(false);
            return threatrepo.save(Threats);
        } else {
            throw new NoSuchElementException("Threats not found with ID: " + threatId);
        }
    }

    public List<ThreatEntity> getAllThreat() {
        return threatrepo.findAll();
    }

    public Map<String, Integer> getThreatCountByDepartment() {
        List<Object[]> results = threatrepo.countThreatsByDepartment();
        Map<String, Integer> countMap = new HashMap<>();
        for (Object[] result : results) {
            String departmentName = (String) result[0];
            Long count = (Long) result[1];
            countMap.put(departmentName, count.intValue());
        }
        return countMap;
    }


    
}

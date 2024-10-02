package com.example.Atlas.Controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Service.DepartmentService;
import com.example.Atlas.Service.OpportunityService;
import com.example.Atlas.Service.StrengthService;
import com.example.Atlas.Service.ThreatService;
import com.example.Atlas.Service.WeaknessService;

@RestController
@RequestMapping("/department")
@CrossOrigin // Allow requests from this origin
public class DepartmentController {
    @Autowired
    DepartmentService departserv;
    @Autowired
    StrengthService strengthService;
    @Autowired
    WeaknessService weaknessService;
    @Autowired
    OpportunityService opportunityService;
    @Autowired
    ThreatService threatService;

    @PostMapping("/register")
    public ResponseEntity<String> registerDepartment(@RequestBody DepartmentEntity department) {
        try {
            departserv.department_register(department);
            return ResponseEntity.ok("Department registered successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/getAllDepartments")
    public Map<String, List<DepartmentEntity>> getAllDepartments() {
    Map<String, List<DepartmentEntity>> response = new HashMap<>();
    response.put("departments", departserv.getAllDepartment());
    return response;
   }  
   
   @GetMapping("/getAllDepartmentsHead")
public Map<String, List<String>> getAllDepartmentsHead() {
    Map<String, List<String>> response = new HashMap<>();
    List<DepartmentEntity> departments = departserv.getAllDepartmentsHead();
    List<String> headOfficers = new ArrayList<>();
    for (DepartmentEntity department : departments) {
        headOfficers.add(department.getHead_officer()); 
    }
    response.put("departmentsHead", headOfficers); // Put the headOfficer names into the map
    return response;
}

   @GetMapping("/getDepartmentCount")
   public Map<String, Integer> getDepartmentCount() {
       Map<String, Integer> response = new HashMap<>();
       response.put("departmentCount", departserv.getDepartmentCount());
       return response;
   }

    @GetMapping("/{departmentId}")
    public DepartmentEntity getProfileData(@PathVariable("departmentId") int departmentId) {
        return departserv.getDepartmentById(departmentId);
    }

    @PutMapping("/update/{departmentId}")
    public ResponseEntity<String> updateDepartmentProfile(
            @PathVariable("departmentId") int departmentId,
            @RequestBody DepartmentEntity request) {
        try {
            boolean success = departserv.updateDepartmentDetails(
                departmentId,
                request.getHead_officer(),
                request.getDepartment_landline(),
                request.getLocation(),
                request.getEmail(),
                request.getUniversity(),
                request.getDescription()
            );
            if (success) {
                return ResponseEntity.ok("Department profile updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Department not found with ID: " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update department profile: " + e.getMessage());
        }
    }

    @GetMapping("/departmentUserCounts")
    public ResponseEntity<Map<String, Integer>> getUserCountsPerDepartment() {
        Map<String, Integer> departmentUserCounts = departserv.getUserCountsPerDepartment();
        return ResponseEntity.ok(departmentUserCounts);
    }

    @GetMapping("/universityCount")
    public Map<String, Integer> getUniversityCount() {
        Map<String, Integer> response = new HashMap<>();
        response.put("universityCount", departserv.getUniversityCount());
        return response;
    }

    @GetMapping("/strengthCountByDepartment")
    public ResponseEntity<Map<String, Integer>> getStrengthCountByDepartment() {
        Map<String, Integer> strengthCountMap = strengthService.getStrengthCountByDepartment();
        return ResponseEntity.ok(strengthCountMap);
    }

    @GetMapping("/weaknessCountByDepartment")
    public ResponseEntity<Map<String, Integer>> getWeaknessCountByDepartment() {
        Map<String, Integer> weaknessCountMap = weaknessService.getWeaknessCountByDepartment();
        return ResponseEntity.ok(weaknessCountMap);
    }

    @GetMapping("/opportunityCountByDepartment")
    public ResponseEntity<Map<String, Integer>> getOpportunityCountByDepartment() {
        Map<String, Integer> opportunityCountMap = opportunityService.getOpportunityCountByDepartment();
        return ResponseEntity.ok(opportunityCountMap);
    }

    @GetMapping("/threatCountByDepartment")
    public ResponseEntity<Map<String, Integer>> getThreatCountByDepartment() {
        Map<String, Integer> threatCountMap = threatService.getThreatCountByDepartment();
        return ResponseEntity.ok(threatCountMap);
    }

}

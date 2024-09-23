package com.example.Atlas.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.Atlas.Entity.FinancialEntity;
import com.example.Atlas.Entity.InternalEntity;
import com.example.Atlas.Entity.LearningEntity;
import com.example.Atlas.Entity.StakeholderEntity;
import com.example.Atlas.Entity.primaryFinancialEntity;
import com.example.Atlas.Entity.primaryInternalEntity;
import com.example.Atlas.Entity.primaryLearningEntity;
import com.example.Atlas.Entity.primaryStakeholderEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Service.BscService;

@RestController
@RequestMapping("/bsc")
@CrossOrigin(origins = "http://localhost:3000")
public class BscController {
    @Autowired
    BscService bscserv;

    @Autowired
    DepartmentRepository departmentrepo;

    @PostMapping("/financialBsc/insert")
    public FinancialEntity insertFinancialBsc(@RequestBody FinancialEntity financial) {
        return bscserv.insertFinancialBsc(financial);
    }

    @GetMapping("financial/get/{departmentId}")
    public ResponseEntity<?> getFinancialByDepartmentId(@PathVariable int departmentId) {
        try {
            List<FinancialEntity> financial = bscserv.findFinancialByDepartmentId(departmentId);
            if (financial != null && !financial.isEmpty()) {
                return ResponseEntity.ok(financial);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No financial found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/financialBsc/getLatestId")
    public ResponseEntity<Map<String, Integer>> getLatestFinancialId() {
        Integer latestId = bscserv.getLatestFinancialId();
        if (latestId != null) {
            Map<String, Integer> response = new HashMap<>();
            response.put("latestId", latestId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/financial/update/{id}")
    public ResponseEntity<FinancialEntity> updateFinancialBsc(@PathVariable int id,
            @RequestBody FinancialEntity request) {
        try {
            FinancialEntity updatedFinancial = bscserv.updateFinancialBscById(id, request);
            return ResponseEntity.ok(updatedFinancial);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Stakeholder
    @PostMapping("/stakeholderBsc/insert")
    public StakeholderEntity insertStakeholderBsc(@RequestBody StakeholderEntity stakeholder) {
        return bscserv.insertStakeholderBsc(stakeholder);
    }

    @GetMapping("stakeholder/get/{departmentId}")
    public ResponseEntity<?> getStakeholderByDepartmentId(@PathVariable int departmentId) {
        try {
            List<StakeholderEntity> stakeholder = bscserv.findStakeholderByDepartmentId(departmentId);
            if (stakeholder != null && !stakeholder.isEmpty()) {
                return ResponseEntity.ok(stakeholder);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No stakeholder found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/stakeholderBsc/getLatestId")
    public ResponseEntity<Map<String, Integer>> getLatestStakeholderId() {
        Integer latestId = bscserv.getLatestStakeholderId();
        if (latestId != null) {
            Map<String, Integer> response = new HashMap<>();
            response.put("latestId", latestId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/stakeholder/update/{id}")
    public ResponseEntity<StakeholderEntity> updateStakeholderBsc(@PathVariable int id,
            @RequestBody StakeholderEntity request) {
        try {
            StakeholderEntity updatedStakeholder = bscserv.updateStakeholderBscById(id, request);
            return ResponseEntity.ok(updatedStakeholder);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Learning
    @PostMapping("/learningBsc/insert")
    public LearningEntity insertlearningBsc(@RequestBody LearningEntity learning) {
        return bscserv.insertLearningBsc(learning);
    }

    @GetMapping("learning/get/{departmentId}")
    public ResponseEntity<?> getLearningByDepartmentId(@PathVariable int departmentId) {
        try {
            List<LearningEntity> learning = bscserv.findLearningByDepartmentId(departmentId);
            if (learning != null && !learning.isEmpty()) {
                return ResponseEntity.ok(learning);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No learning found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/learningBsc/getLatestId")
    public ResponseEntity<Map<String, Integer>> getLatestLearningId() {
        Integer latestId = bscserv.getLatestLearningId();
        if (latestId != null) {
            Map<String, Integer> response = new HashMap<>();
            response.put("latestId", latestId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/learning/update/{id}")
    public ResponseEntity<LearningEntity> updateLearningBsc(@PathVariable int id,
            @RequestBody LearningEntity request) {
        try {
            LearningEntity updatedLearning = bscserv.updateLearningBscById(id, request);
            return ResponseEntity.ok(updatedLearning);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Intenal
    @PostMapping("/internalBsc/insert")
    public InternalEntity insertInternalBsc(@RequestBody InternalEntity internal) {
        return bscserv.insertInternalBsc(internal);
    }

    @GetMapping("internal/get/{departmentId}")
    public ResponseEntity<?> getInternalByDepartmentId(@PathVariable int departmentId) {
        try {
            List<InternalEntity> internal = bscserv.findInternalByDepartmentId(departmentId);
            if (internal != null && !internal.isEmpty()) {
                return ResponseEntity.ok(internal);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No internal found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/internalBsc/getLatestId")
    public ResponseEntity<Map<String, Integer>> getLatestInternalId() {
        Integer latestId = bscserv.getLatestInternalId();
        if (latestId != null) {
            Map<String, Integer> response = new HashMap<>();
            response.put("latestId", latestId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/internal/update/{id}")
    public ResponseEntity<InternalEntity> updateInternalBsc(@PathVariable int id,
            @RequestBody InternalEntity request) {
        try {
            InternalEntity updatedInternal = bscserv.updateInternalBscById(id, request);
            return ResponseEntity.ok(updatedInternal);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/financialBscCount")
    public Map<String, Integer> getFinancialCount() {
        Map<String, Integer> response = new HashMap<>();
        int count = bscserv.getFinancialCount();
        response.put("count", count);
        return response;
    }

    @GetMapping("/stakeholderBscCount")
    public Map<String, Integer> getStakeholderCount() {
        Map<String, Integer> response = new HashMap<>();
        int count = bscserv.getStakeholderCount();
        response.put("count", count);
        return response;
    }

    @GetMapping("/learningBscCount")
    public Map<String, Integer> getLearningCount() {
        Map<String, Integer> response = new HashMap<>();
        int count = bscserv.getLearningCount();
        response.put("count", count);
        return response;
    }

    @GetMapping("/internalBscCount")
    public Map<String, Integer> getInternalCount() {
        Map<String, Integer> response = new HashMap<>();
        int count = bscserv.getInternalCount();
        response.put("count", count);
        return response;
    }

    // Primary Functions

    // Primary Financial
    @PostMapping("primaryFinancialBsc/insert")
    public primaryFinancialEntity insertPrimaryFinancialBsc(@RequestBody primaryFinancialEntity financial) {
        return bscserv.insertPrimaryFinancialBsc(financial);
    }

    @GetMapping("primaryFinancial/get/{departmentId}")
    public ResponseEntity<?> getPrimaryFinancialByDepartmentId(@PathVariable int departmentId) {
        try {
            List<primaryFinancialEntity> financial = bscserv.findPrimaryFinancialByDepartmentId(departmentId);
            if (financial != null && !financial.isEmpty()) {
                return ResponseEntity.ok(financial);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No primary financial found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/primaryFinancialBsc/getLatestId")
    public ResponseEntity<Map<String, Integer>> getLatestPrimaryFinancialId() {
        Integer latestId = bscserv.getLatestPrimaryFinancialId();
        if (latestId != null) {
            Map<String, Integer> response = new HashMap<>();
            response.put("latestId", latestId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/primaryFinancial/update/{id}")
    public ResponseEntity<primaryFinancialEntity> updatePrimaryFinancialBsc(@PathVariable int id,
            @RequestBody primaryFinancialEntity request) {
        try {
            primaryFinancialEntity updatedFinancial = bscserv.updatePrimaryFinancialBscById(id, request);
            return ResponseEntity.ok(updatedFinancial);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Primary Stakeholder
    @PostMapping("primaryStakeholderBsc/insert")
    public primaryStakeholderEntity insertPrimaryStakeholderBsc(@RequestBody primaryStakeholderEntity stakeholder) {
        return bscserv.insertPrimaryStakeholderBsc(stakeholder);
    }

    @GetMapping("primaryStakeholder/get/{departmentId}")
    public ResponseEntity<?> getPrimaryStakeholderByDepartmentId(@PathVariable int departmentId) {
        try {
            List<primaryStakeholderEntity> stakeholder = bscserv.findPrimaryStakeholderByDepartmentId(departmentId);
            if (stakeholder != null && !stakeholder.isEmpty()) {
                return ResponseEntity.ok(stakeholder);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No primary stakeholder found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Primary Internal
    @PostMapping("primaryInternalBsc/insert")
    public primaryInternalEntity insertPrimaryInternalBsc(@RequestBody primaryInternalEntity internal) {
        return bscserv.insertPrimaryInternalBsc(internal);
    }

    @GetMapping("primaryInternal/get/{departmentId}")
    public ResponseEntity<?> getPrimaryInternalByDepartmentId(@PathVariable int departmentId) {
        try {
            List<primaryInternalEntity> internal = bscserv.findPrimaryInternalByDepartmentId(departmentId);
            if (internal != null && !internal.isEmpty()) {
                return ResponseEntity.ok(internal);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No primary internal found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/primaryInternalBsc/getLatestId")
    public ResponseEntity<Map<String, Integer>> getLatestPrimaryInternalId() {
        Integer latestId = bscserv.getLatestPrimaryInternalId();
        if (latestId != null) {
            Map<String, Integer> response = new HashMap<>();
            response.put("latestId", latestId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/primaryInternal/update/{id}")
    public ResponseEntity<primaryInternalEntity> updatePrimaryInternalBsc(@PathVariable int id,
            @RequestBody primaryInternalEntity request) {
        try {
            primaryInternalEntity updatedInternal = bscserv.updatePrimaryInternalBscById(id, request);
            return ResponseEntity.ok(updatedInternal);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Primary Learning
    @PostMapping("primaryLearningBsc/insert")
    public primaryLearningEntity insertPrimaryLearningBsc(@RequestBody primaryLearningEntity learning) {
        return bscserv.insertPrimaryLearningBsc(learning);
    }

    @GetMapping("primaryLearning/get/{departmentId}")
    public ResponseEntity<?> getPrimaryLearningByDepartmentId(@PathVariable int departmentId) {
        try {
            List<primaryLearningEntity> learning = bscserv.findPrimaryLearningByDepartmentId(departmentId);
            if (learning != null && !learning.isEmpty()) {
                return ResponseEntity.ok(learning);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No primary learning found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/primaryLearningBsc/getLatestId")
    public ResponseEntity<Map<String, Integer>> getLatestPrimaryLearningId() {
        Integer latestId = bscserv.getLatestPrimaryLearningId();
        if (latestId != null) {
            Map<String, Integer> response = new HashMap<>();
            response.put("latestId", latestId);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/primaryLearning/update/{id}")
    public ResponseEntity<primaryLearningEntity> updatePrimaryLearningBsc(@PathVariable int id,
            @RequestBody primaryLearningEntity request) {
        try {
            primaryLearningEntity updatedLearning = bscserv.updatePrimaryLearningBscById(id, request);
            return ResponseEntity.ok(updatedLearning);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

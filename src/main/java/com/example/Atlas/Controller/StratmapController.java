package com.example.Atlas.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Atlas.Entity.FinancialEntity;
import com.example.Atlas.Entity.primaryFinancialEntity;
import com.example.Atlas.Entity.StakeholderEntity;
import com.example.Atlas.Entity.primaryStakeholderEntity;
import com.example.Atlas.Entity.LearningEntity;
import com.example.Atlas.Entity.primaryLearningEntity;
import com.example.Atlas.Entity.InternalEntity;
import com.example.Atlas.Entity.primaryInternalEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.FinancialRepository;
import com.example.Atlas.Service.StratmapService;

@RestController
@RequestMapping("/stratmap")
@CrossOrigin()
public class StratmapController {
    @Autowired
    StratmapService stratmapserv;

    @Autowired
    DepartmentRepository departmentrepo;

    @Autowired
    FinancialRepository financialrepo;

    @GetMapping("byDepartment/{departmentId}")
    public ResponseEntity<Map<String, Object>> getStrategiesByDepartmentId(@PathVariable int departmentId) {
        try {
            Map<String, Object> strategies = stratmapserv.getStrategiesByDepartmentId(departmentId);
            return ResponseEntity.ok(strategies);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to fetch strategies"));
        }
    }

    @PostMapping("/financial/insert")
    public FinancialEntity insertFinancial(@RequestBody FinancialEntity financial) {
        return stratmapserv.insertFinancial(financial);
    }

    @GetMapping("/financial/get/{departmentId}")
    public ResponseEntity<?> getFinancialByDepartmentId(@PathVariable int departmentId) {
        try {
            List<FinancialEntity> financial = stratmapserv.getFinancialByDepartmentId(departmentId);
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

    @PostMapping("/primaryFinancial/insert")
    public primaryFinancialEntity insertPrimaryFinancial(@RequestBody primaryFinancialEntity primaryFinancial) {
        return stratmapserv.insertPrimaryFinancial(primaryFinancial);
    }

    @GetMapping("primaryFinancial/get/{departmentId}")
    public ResponseEntity<?> getPrimaryFinancialByDepartmentId(@PathVariable int departmentId) {
        try {
            List<primaryFinancialEntity> primaryFinancial = stratmapserv
                    .getPrimaryFinancialByDepartmentId(departmentId);
            if (primaryFinancial != null && !primaryFinancial.isEmpty()) {
                return ResponseEntity.ok(primaryFinancial);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No primary financial found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("financial/delete/{id}")
    public ResponseEntity<String> deleteFinancial(@PathVariable int id) {
        try {
            stratmapserv.deleteFinancial(id);
            return ResponseEntity.ok("Financial with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete financial: " + e.getMessage());
        }
    }

    @DeleteMapping("stakeholder/delete/{id}")
    public ResponseEntity<String> deleteStakeholder(@PathVariable int id) {
        try {
            stratmapserv.deleteStakeholder(id);
            return ResponseEntity.ok("Stakeholder with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete stakeholder: " + e.getMessage());
        }
    }

    @PostMapping("/primaryStakeholder/insert")
    public primaryStakeholderEntity insertPrimaryStakeholder(@RequestBody primaryStakeholderEntity primaryStakeholder) {
        return stratmapserv.insertPrimaryStakeholder(primaryStakeholder);
    }

    @GetMapping("primaryStakeholder/get/{departmentId}")
    public ResponseEntity<?> getPrimaryStakeholderByDepartmentId(@PathVariable int departmentId) {
        try {
            List<primaryStakeholderEntity> primaryStakeholder = stratmapserv
                    .getPrimaryStakeholderByDepartmentId(departmentId);
            if (primaryStakeholder != null && !primaryStakeholder.isEmpty()) {
                return ResponseEntity.ok(primaryStakeholder);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No primary stakeholder found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());

        }
    }

    @DeleteMapping("learning/delete/{id}")
    public ResponseEntity<String> deleteLearning(@PathVariable int id) {
        try {
            stratmapserv.deleteLearning(id);
            return ResponseEntity.ok("Learning with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete learning: " + e.getMessage());
        }
    }

    @DeleteMapping("internal/delete/{id}")
    public ResponseEntity<String> deleteInternal(@PathVariable int id) {
        try {
            stratmapserv.deleteInternal(id);
            return ResponseEntity.ok("Internal with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete internal: " + e.getMessage());
        }
    }

    @GetMapping("primaryInternal/get/{departmentId}")
    public ResponseEntity<?> getPrimaryInternalByDepartmentId(@PathVariable int departmentId) {
        try {
            List<primaryInternalEntity> primaryInternal = stratmapserv.getPrimaryInternalByDepartmentId(departmentId);
            if (primaryInternal != null && !primaryInternal.isEmpty()) {
                return ResponseEntity.ok(primaryInternal);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No primary internal found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());

        }
    }

    @PostMapping("/primaryInternal/insert")
    public primaryInternalEntity insertPrimaryInternal(@RequestBody primaryInternalEntity primaryInternal) {
        return stratmapserv.insertPrimaryInternal(primaryInternal);
    }

    @PostMapping("/stakeholder/insert")
    public StakeholderEntity insertStakeholder(@RequestBody StakeholderEntity stakeholder) {
        return stratmapserv.insertStakeholder(stakeholder);
    }

    @GetMapping("/stakeholder/get/{departmentId}")
    public ResponseEntity<?> getStakeholderByDepartmentId(@PathVariable int departmentId) {
        try {
            List<StakeholderEntity> stakeholder = stratmapserv.getStakeholderByDepartmentId(departmentId);
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

    @PostMapping("/learning/insert")
    public LearningEntity insertLearning(@RequestBody LearningEntity learning) {
        return stratmapserv.insertLearning(learning);
    }

    @GetMapping("/learning/get/{departmentId}")
    public ResponseEntity<?> getLearningByDepartmentId(@PathVariable int departmentId) {
        try {
            List<LearningEntity> learning = stratmapserv.getLearningByDepartmentId(departmentId);
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

    @PostMapping("/primaryLearning/insert")
    public primaryLearningEntity insertPrimaryLearning(@RequestBody primaryLearningEntity primaryLearning) {
        return stratmapserv.insertPrimaryLearning(primaryLearning);
    }

    @GetMapping("primaryLearning/get/{departmentId}")
    public ResponseEntity<?> getPrimaryLearningByDepartmentId(@PathVariable int departmentId) {
        try {
            List<primaryLearningEntity> primaryLearning = stratmapserv.getPrimaryLearningByDepartmentId(departmentId);
            if (primaryLearning != null && !primaryLearning.isEmpty()) {
                return ResponseEntity.ok(primaryLearning);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No primary learning found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/internal/insert")
    public InternalEntity insertInternal(@RequestBody InternalEntity internal) {
        return stratmapserv.insertInternal(internal);
    }

    @GetMapping("/internal/get/{departmentId}")
    public ResponseEntity<?> getInternalByDepartmentId(@PathVariable int departmentId) {
        try {
            List<InternalEntity> internal = stratmapserv.getInternalByDepartmentId(departmentId);
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

    @PutMapping("/financial/edit/{id}")
    public ResponseEntity<?> editFinancial(@PathVariable int id, @RequestBody FinancialEntity financialEntity) {
        try {
            FinancialEntity updatedFinancial = stratmapserv.editFinancialEntity(id, financialEntity);
            return ResponseEntity.ok(updatedFinancial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/stakeholder/edit/{id}")
    public ResponseEntity<?> editStakeholder(@PathVariable int id, @RequestBody StakeholderEntity stakeholderEntity) {
        try {
            StakeholderEntity updatedStakeholder = stratmapserv.editStakeholderEntity(id, stakeholderEntity);
            return ResponseEntity.ok(updatedStakeholder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/learning/edit/{id}")
    public ResponseEntity<?> editLearning(@PathVariable int id, @RequestBody LearningEntity learningEntity) {
        try {
            LearningEntity updatedLearning = stratmapserv.editLearningEntity(id, learningEntity);
            return ResponseEntity.ok(updatedLearning);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/internal/edit/{id}")
    public ResponseEntity<?> editInternal(@PathVariable int id, @RequestBody InternalEntity internalEntity) {
        try {
            InternalEntity updatedInternal = stratmapserv.editInternalEntity(id, internalEntity);
            return ResponseEntity.ok(updatedInternal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getByTargetCode/{targetCode}")
    public ResponseEntity<?> getDataByTargetCode(@PathVariable String targetCode) {
        try {
            List<Map<String, Object>> combinedData = new ArrayList<>();

            // Handle null targetCode gracefully
            if (targetCode != null && !targetCode.isEmpty()) {
                List<primaryLearningEntity> learningData = stratmapserv.getLearningByTargetCode(targetCode);
                List<primaryStakeholderEntity> stakeholderData = stratmapserv.getStakeholderByTargetCode(targetCode);
                List<primaryFinancialEntity> financialData = stratmapserv.getFinancialByTargetCode(targetCode);
                List<primaryInternalEntity> internalData = stratmapserv.getInternalByTargetCode(targetCode);
                combinedData.addAll(
                        learningData.stream().map(this::transformLearningEntityToMap).collect(Collectors.toList()));
                combinedData.addAll(stakeholderData.stream().map(this::transformStakeholderEntityToMap)
                        .collect(Collectors.toList()));
                combinedData.addAll(
                        financialData.stream().map(this::transformFinancialEntityToMap).collect(Collectors.toList()));
                combinedData.addAll(
                        internalData.stream().map(this::transformInternalEntityToMap).collect(Collectors.toList()));
            } else {
                // Handle the case where targetCode is null or empty. Return an empty list.
                return ResponseEntity.ok(combinedData);
            }

            if (combinedData.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(combinedData);

        } catch (Exception e) {
            // Log the exception for debugging purposes. Remove e.printStackTrace() in
            // production.
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to fetch data: " + e.getMessage()));
        }
    }

    // Separate helper functions for each entity type. Null values are replaced with
    // 0.
    private Map<String, Object> transformLearningEntityToMap(primaryLearningEntity entity) {
        return Map.of(
                "target_code", entity.getTarget_code(),
                "target_performance", objectToZeroIfNull(entity.getTarget_performance()),
                "actual_performance", objectToZeroIfNull(entity.getActual_performance()),
                "office_target", objectToZeroIfNull(entity.getOffice_target()),
                "department_id", entity.getDepartment().getId(),
                "department_name", entity.getDepartment().getDepartment_name(),
                "entityType", "learning");
    }

    private Map<String, Object> transformStakeholderEntityToMap(primaryStakeholderEntity entity) {
        return Map.of(
                "target_code", entity.getTarget_code(),
                "target_performance", objectToZeroIfNull(entity.getTarget_performance()),
                "actual_performance", objectToZeroIfNull(entity.getActual_performance()),
                "office_target", objectToZeroIfNull(entity.getOffice_target()),
                "department_id", entity.getDepartment().getId(),
                "department_name", entity.getDepartment().getDepartment_name(),
                "entityType", "stakeholder");
    }

    private Map<String, Object> transformFinancialEntityToMap(primaryFinancialEntity entity) {
        return Map.of(
                "target_code", entity.getTarget_code(),
                "target_performance", objectToZeroIfNull(entity.getTarget_performance()),
                "actual_performance", objectToZeroIfNull(entity.getActual_performance()),
                "office_target", objectToZeroIfNull(entity.getOffice_target()),
                "department_id", entity.getDepartment().getId(),
                "department_name", entity.getDepartment().getDepartment_name(),
                "entityType", "financial");
    }

    private Map<String, Object> transformInternalEntityToMap(primaryInternalEntity entity) {
        return Map.of(
                "target_code", entity.getTarget_code(),
                "target_performance", objectToZeroIfNull(entity.getTarget_performance()),
                "actual_performance", objectToZeroIfNull(entity.getActual_performance()),
                "office_target", objectToZeroIfNull(entity.getOffice_target()),
                "department_id", entity.getDepartment().getId(),
                "department_name", entity.getDepartment().getDepartment_name(),
                "entityType", "internal");
    }

    // Helper function to handle null values
    private Object objectToZeroIfNull(Object obj) {
        if (obj == null) {
            return 0; // Replace null with 0
        }
        return obj;
    }

}
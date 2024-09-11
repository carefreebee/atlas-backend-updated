package com.example.Atlas.Controller;

import java.util.NoSuchElementException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Atlas.Entity.OpportunityEntity;
import com.example.Atlas.Service.OpportunityService;


@RestController
@RequestMapping("/opportunities")
@CrossOrigin
public class OpportunityController {
    @Autowired
    OpportunityService opportunityserv;

    @PostMapping("insert")
    public OpportunityEntity insertOpportunity(@RequestBody OpportunityEntity opportunity) {
        return opportunityserv.insertOpportunity(opportunity);
    }
    
    @GetMapping("/get/{departmentId}")
    public ResponseEntity<?> getOpportunityByDepartmentId(@PathVariable int departmentId) {
        try {
            List<OpportunityEntity> opportunity = opportunityserv.getOpportunityByDepartmentId(departmentId);
            return ResponseEntity.ok(opportunity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    

    @PutMapping("/update/{departmentId}")
    public ResponseEntity<?> updateOpportunityById(@PathVariable int departmentId, @RequestBody OpportunityEntity request) {
        try {
            request.getDepartment().setId(departmentId);
            OpportunityEntity updatedOpportunity = opportunityserv.updateOpportunityById(request);
            return ResponseEntity.ok(updatedOpportunity); // Return the updated entity directly
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOpportunity(@PathVariable int id) {
        try {
            opportunityserv.deleteOpportunity(id);
            return ResponseEntity.ok("Opportunity with ID " + id + " opportunity successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete opportunity: " + e.getMessage());
        }
    }

    // added
    @GetMapping("/deleted/{departmentId}")
    public ResponseEntity<?> getDeletedOpportunites(@PathVariable int departmentId) {
        try {
            List<OpportunityEntity> deletedOpportunity = opportunityserv.getDeletedOpportunities(departmentId);
            return ResponseEntity.ok(deletedOpportunity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreOpportunity(@PathVariable int id) {
        try {
            OpportunityEntity restoredOpportunity = opportunityserv.restoreOpportunity(id);
            return ResponseEntity.ok(restoredOpportunity);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllOpportunity() {
        try {
            List<OpportunityEntity> opportunities = opportunityserv.getAllOpportunity();
            if (opportunities != null && !opportunities.isEmpty()) {
                return ResponseEntity.ok(opportunities);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No opportunities found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }



    
}

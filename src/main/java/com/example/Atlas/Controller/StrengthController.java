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

import com.example.Atlas.Entity.StrengthEntity;
import com.example.Atlas.Service.StrengthService;

@RestController
@RequestMapping("/strengths")
@CrossOrigin
public class StrengthController {
    @Autowired
    StrengthService strengthserv;

     @PostMapping("insert")
    public StrengthEntity insertStrength(@RequestBody StrengthEntity strength) {
        return strengthserv.insertStrength(strength);
    }
    

    @GetMapping("/get/{departmentId}")
    public ResponseEntity<?> getStrengthByDepartmentId(@PathVariable int departmentId) {
        try {
            List<StrengthEntity> strengths = strengthserv.getStrengthsByDepartmentId(departmentId);
                return ResponseEntity.ok(strengths);       
	} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    

    @PutMapping("/update/{departmentId}")
    public ResponseEntity<?> updateStrengthById(@PathVariable int departmentId, @RequestBody StrengthEntity request) {
        try {
            request.getDepartment().setId(departmentId);
            StrengthEntity updatedStrength = strengthserv.updateStrengthById(request);
            return ResponseEntity.ok(updatedStrength); // Return the updated entity directly
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStrength(@PathVariable int id) {
        try {
            strengthserv.deleteStrength(id);
            return ResponseEntity.ok("Strength with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete strength: " + e.getMessage());
        }
    }


    // added
    @GetMapping("/deleted/{departmentId}")
    public ResponseEntity<?> getDeletedStrengths(@PathVariable int departmentId) {
        try {
            List<StrengthEntity> deletedStrengths = strengthserv.getDeletedStrengths(departmentId);
            return ResponseEntity.ok(deletedStrengths);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreStrength(@PathVariable int id) {
        try {
            StrengthEntity restoredStrength = strengthserv.restoreStrength(id);
            return ResponseEntity.ok(restoredStrength);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStrengths() {
        try {
            List<StrengthEntity> strengths = strengthserv.getAllStrengths();
            if (strengths != null && !strengths.isEmpty()) {
                return ResponseEntity.ok(strengths);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No strengths found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
 



}

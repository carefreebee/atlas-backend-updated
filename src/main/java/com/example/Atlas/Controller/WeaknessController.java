package com.example.Atlas.Controller;


import java.util.List;
import java.util.NoSuchElementException;

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

import com.example.Atlas.Entity.WeaknessEntity;
import com.example.Atlas.Service.WeaknessService;

@RestController
@RequestMapping("/weaknesses")
@CrossOrigin
public class WeaknessController {
    @Autowired
    WeaknessService weaknessserv;

    @PostMapping("insert")
    public WeaknessEntity insertWeakness(@RequestBody WeaknessEntity weakness) {
        return weaknessserv.insertWeakness(weakness);
    }
    

    @GetMapping("/get/{departmentId}")
    public ResponseEntity<?> getWeaknessByDepartmentId(@PathVariable int departmentId) {
        try {
            List<WeaknessEntity> weakness = weaknessserv.getWeaknessByDepartmentId(departmentId);
                return ResponseEntity.ok(weakness);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{departmentId}")
    public ResponseEntity<?> updateWeaknessById(@PathVariable int departmentId, @RequestBody WeaknessEntity request) {
        try {
            request.getDepartment().setId(departmentId);
            WeaknessEntity updatedWeakness = weaknessserv.updateWeaknessById(request);
            return ResponseEntity.ok(updatedWeakness); // Return the updated entity directly
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWeakness(@PathVariable int id) {
        try {
            weaknessserv.deleteWeakness(id);
            return ResponseEntity.ok("Weakness with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete weakness: " + e.getMessage());
        }
    }

     // added
    @GetMapping("/deleted/{departmentId}")
    public ResponseEntity<?> getDeletedWeaknesses(@PathVariable int departmentId) {
        try {
            List<WeaknessEntity> deletedWeakness = weaknessserv.getDeletedWeaknesses(departmentId);
            return ResponseEntity.ok(deletedWeakness);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreWeakness(@PathVariable int id) {
        try {
            WeaknessEntity restoredWeakness = weaknessserv.restoreWeakness(id);
            return ResponseEntity.ok(restoredWeakness);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllWeakness() {
        try {
            List<WeaknessEntity> weakness = weaknessserv.getAllWeakness();
            if (weakness != null && !weakness.isEmpty()) {
                return ResponseEntity.ok(weakness);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No weakness found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    
}

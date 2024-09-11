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

import com.example.Atlas.Entity.ThreatEntity;
import com.example.Atlas.Service.ThreatService;

@RestController
@RequestMapping("/threats")
@CrossOrigin
public class ThreatController {
    @Autowired
    ThreatService threatserv;


    @PostMapping("insert")
    public ThreatEntity insertThreat(@RequestBody ThreatEntity threat) {
        return threatserv.insertThreat(threat);
    }
    

    @GetMapping("/get/{departmentId}")
    public ResponseEntity<?> getThreatsByDepartmentId(@PathVariable int departmentId) {
        try {
            List<ThreatEntity> threats = threatserv.getThreatsByDepartmentId(departmentId);
                return ResponseEntity.ok(threats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/update/{departmentId}")
    public ResponseEntity<?> updateThreatById(@PathVariable int departmentId, @RequestBody ThreatEntity request) {
        try {
            request.getDepartment().setId(departmentId);
            ThreatEntity updatedThreat = threatserv.updateThreatById(request);
            return ResponseEntity.ok(updatedThreat); // Return the updated entity directly
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteThreat(@PathVariable int id) {
        try {
            threatserv.deleteThreat(id);
            return ResponseEntity.ok("Threat with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete threat: " + e.getMessage());
        }
    }

    // added
    @GetMapping("/deleted/{departmentId}")
    public ResponseEntity<?> getDeletedThreats(@PathVariable int departmentId) {
        try {
            List<ThreatEntity> deletedThreats = threatserv.getDeletedThreats(departmentId);
            return ResponseEntity.ok(deletedThreats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restorethreat(@PathVariable int id) {
        try {
            ThreatEntity restoredThreat = threatserv.restoreThreat(id);
            return ResponseEntity.ok(restoredThreat);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllThreat() {
        try {
            List<ThreatEntity> threat = threatserv.getAllThreat();
            if (threat != null && !threat.isEmpty()) {
                return ResponseEntity.ok(threat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No threat found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


}

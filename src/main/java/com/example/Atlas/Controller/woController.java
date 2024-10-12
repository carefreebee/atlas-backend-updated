package com.example.Atlas.Controller;

import java.util.HashMap;
import java.util.Map;
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

import com.example.Atlas.Entity.woEntity;
import com.example.Atlas.Service.woService;

@RestController
@RequestMapping("/woStrat")
@CrossOrigin
public class woController {
    @Autowired
    woService woserv;

     @PostMapping("insert")
    public woEntity insertWoStrat(@RequestBody woEntity woStrat) {
        return woserv.insertWoStrat(woStrat);
    }
    

    @GetMapping("/getNonSorted/{departmentId}")
    public ResponseEntity<?> getWoStratByDepartmentIdNonSorted(@PathVariable int departmentId) {
        try {
            List<woEntity> woStrat = woserv.getWoStratByDepartmentIdNonSorted(departmentId);
            if (woStrat != null && !woStrat.isEmpty()) {
                return ResponseEntity.ok(woStrat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No W-OStrat found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("get/{departmentId}")
    public ResponseEntity<?> getWoStratByDepartmentId(@PathVariable int departmentId) {
        try {
            List<woEntity> woStrat = woserv.getWoStratByDepartmentId(departmentId);
            if (woStrat != null && !woStrat.isEmpty()) {
                return ResponseEntity.ok(woStrat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No W-OStrat found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
public ResponseEntity<Map<String, String>> deleteWo(@PathVariable int id) {
    Map<String, String> response = new HashMap<>();
    
    try {
        woserv.deleteWo(id); // Make sure this throws a NoSuchElementException if the ID does not exist
        response.put("message", "W-O with ID " + id + " deleted successfully");
        return ResponseEntity.ok(response);
        
    } catch (NoSuchElementException e) {
        response.put("error", "W-O with ID " + id + " not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        
    } catch (Exception e) {
        response.put("error", "Failed to delete W-O: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

    @PutMapping("/updateSorted/{id}")
    public ResponseEntity<?> updateSorted(@PathVariable int id) {
        try {
            woEntity wo = woserv.updateSortedStatus(id);
            if (wo != null) {
                return ResponseEntity.ok(wo);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("WO Strat not found with id " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating WO Strat: " + e.getMessage());
        }
    }

    
}

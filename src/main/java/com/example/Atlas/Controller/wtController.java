package com.example.Atlas.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Map;

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


import com.example.Atlas.Entity.wtEntity;
import com.example.Atlas.Service.wtService;

@RestController
@RequestMapping("/wtStrat")
@CrossOrigin
public class wtController {
    @Autowired
    wtService wtserv;

     @PostMapping("insert")
    public wtEntity insertWtStrat(@RequestBody wtEntity wtStrat) {
        return wtserv.insertWtStrat(wtStrat);
    }
    

    @GetMapping("/getNonSorted/{departmentId}")
    public ResponseEntity<?> getWtStratByDepartmentIdNonSorted(@PathVariable int departmentId) {
        try {
            List<wtEntity> wtStrat = wtserv.getWtStratByDepartmentIdNonSorted(departmentId);
            if (wtStrat != null && !wtStrat.isEmpty()) {
                return ResponseEntity.ok(wtStrat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No W-TStrat found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("get/{departmentId}")
    public ResponseEntity<?> getWtStratByDepartmentId(@PathVariable int departmentId) {
        try {
            List<wtEntity> wtStrat = wtserv.getWtStratByDepartmentId(departmentId);
            if (wtStrat != null && !wtStrat.isEmpty()) {
                return ResponseEntity.ok(wtStrat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No W-TStrat found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
public ResponseEntity<Map<String, String>> deleteWt(@PathVariable int id) {
    Map<String, String> response = new HashMap<>();
    
    try {
        wtserv.deleteWt(id); // Make sure this throws a NoSuchElementException if the ID does not exist
        response.put("message", "W-T with ID " + id + " deleted successfully");
        return ResponseEntity.ok(response);
        
    } catch (NoSuchElementException e) {
        response.put("error", "W-T with ID " + id + " not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        
    } catch (Exception e) {
        response.put("error", "Failed to delete W-T: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

    @PutMapping("/updateSorted/{id}")
    public ResponseEntity<?> updateSorted(@PathVariable int id) {
        try {
            wtEntity wt = wtserv.updateSortedStatus(id);
            if (wt != null) {
                return ResponseEntity.ok(wt);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("WT Strat not found with id " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating WT Strat: " + e.getMessage());
        }
    }
    
}

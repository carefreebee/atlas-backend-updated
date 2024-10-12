package com.example.Atlas.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.example.Atlas.Entity.soEntity;
import com.example.Atlas.Service.soService;

@RestController
@RequestMapping("/soStrat")
@CrossOrigin
public class soController {
    @Autowired
    soService soserv;

     @PostMapping("insert")
    public soEntity insertSoStrat(@RequestBody soEntity soStrat) {
        return soserv.insertSoStrat(soStrat);
    }
    

    @GetMapping("/getNonSorted/{departmentId}")
    public ResponseEntity<?> getSoStratByDepartmentIdNonSorted(@PathVariable int departmentId) {
        try {
            List<soEntity> soStrat = soserv.getSoStratByDepartmentIdNonSorted(departmentId);
            if (soStrat != null && !soStrat.isEmpty()) {
                return ResponseEntity.ok(soStrat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No S-OStrat found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("get/{departmentId}")
    public ResponseEntity<?> getSoStratByDepartmentId(@PathVariable int departmentId) {
        try {
            List<soEntity> soStrat = soserv.getSoStratByDepartmentId(departmentId);
            if (soStrat != null && !soStrat.isEmpty()) {
                return ResponseEntity.ok(soStrat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No S-OStrat found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteSo(@PathVariable int id) {
    Map<String, String> response = new HashMap<>();
        
        try {
            soserv.deleteSo(id); // Make sure this throws a NoSuchElementException if the ID does not exist
            response.put("message", "S-O with ID " + id + " deleted successfully");
            return ResponseEntity.ok(response);
            
        } catch (NoSuchElementException e) {
            response.put("error", "S-O with ID " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            
        } catch (Exception e) {
            response.put("error", "Failed to delete S-O: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/updateSorted/{id}")
    public ResponseEntity<?> updateSorted(@PathVariable int id) {
        try {
            soEntity so = soserv.updateSortedStatus(id);
            if (so != null) {
                return ResponseEntity.ok(so);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SO Strat not found with id " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating SO Strat: " + e.getMessage());
        }
    }
}

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Atlas.Entity.stEntity;
import com.example.Atlas.Service.stService;

@RestController
@RequestMapping("/stStrat")
@CrossOrigin
public class stController {
    @Autowired
    stService stserv;

     @PostMapping("insert")
    public stEntity insertStStrat(@RequestBody stEntity stStrat) {
        return stserv.insertStStrat(stStrat);
    }
    

    @GetMapping("/get/{departmentId}")
    public ResponseEntity<?> getStStratByDepartmentId(@PathVariable int departmentId) {
        try {
            List<stEntity> stStrat = stserv.getStStratByDepartmentId(departmentId);
            if (stStrat != null && !stStrat.isEmpty()) {
                return ResponseEntity.ok(stStrat);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No S-TStrat found for department id " + departmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    //   @DeleteMapping("/delete/{id}")
    // public ResponseEntity<String> deleteSt(@PathVariable int id) {
    //     try {
    //         stserv.deleteSt(id);
    //         return ResponseEntity.ok("S-T with ID " + id + " deleted successfully");
    //     } catch (NoSuchElementException e) {
    //         return ResponseEntity.notFound().build();
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Failed to delete S-T: " + e.getMessage());
    //     }
    // }

     @DeleteMapping("/delete/{id}")
public ResponseEntity<Map<String, String>> deleteSt(@PathVariable int id) {
    Map<String, String> response = new HashMap<>();
    
    try {
        stserv.deleteSt(id); // Make sure this throws a NoSuchElementException if the ID does not exist
        response.put("message", "S-T with ID " + id + " deleted successfully");
        return ResponseEntity.ok(response);
        
    } catch (NoSuchElementException e) {
        response.put("error", "S-T with ID " + id + " not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        
    } catch (Exception e) {
        response.put("error", "Failed to delete S-T: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
    
}

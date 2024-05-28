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
    

    @GetMapping("/get/{departmentId}")
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
    public ResponseEntity<String> deleteSo(@PathVariable int id) {
        try {
            soserv.deleteSo(id);
            return ResponseEntity.ok("S-O with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete S-O: " + e.getMessage());
        }
    }

}

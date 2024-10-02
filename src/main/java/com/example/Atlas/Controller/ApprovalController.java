package com.example.Atlas.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Atlas.Entity.ApprovalEntity;
import com.example.Atlas.Service.ApprovalService;
import com.example.Atlas.Service.DepartmentService;



@RestController
@RequestMapping("/approval")
@CrossOrigin(origins = "http://localhost:3000")
public class ApprovalController {

     @Autowired
    ApprovalService aserv;

    @Autowired
    DepartmentService departserv;

    @PostMapping("/insert")
    public ApprovalEntity insertApproval(@RequestBody ApprovalEntity approval) {
        return aserv.insertApproval(approval);
    }
    
     @GetMapping("/get/{departmentId}")
    public ResponseEntity<?> getApprovalByDepartmentId(@PathVariable int departmentId) {
        try {
            List<ApprovalEntity> approval = aserv.getApprovalByDepartmentId(departmentId);
                return ResponseEntity.ok(approval);       
	} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    // @PutMapping("/update/{department_id}")
    // public ResponseEntity<String> updateApprovalDetails(
    //         @PathVariable("department_id") int department_id,
    //         @RequestBody ApprovalEntity request) {
    //     try {
    //         boolean success = aserv.updateApprovalDetails(
    //                department_id,
    //                request.getAcknowledgedByName(),
    //                request.getPreparedByName(),
    //                request.getPreparedByRole(),
    //                request.getAcknowledgedByRole(),
    //                request.getReviewedByName(),
    //                request.getReviewedByRole()
                   
    //         );
    //         if (success) {
    //             return ResponseEntity.ok("Approval updated successfully.");
    //         } else {
    //             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Approval not found with ID: " + department_id);
    //         }
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Approval: " + e.getMessage());
    //     }
    // }
    
    @PutMapping("/update/{department_id}")
    public ResponseEntity<String> updateApprovalDetails(
            @PathVariable("department_id") int department_id,
            @RequestBody ApprovalEntity request) {
        try {
            boolean success = aserv.updateApprovalDetailsByDepartmentId(
                    department_id,
                    request.getPreparedByName(),
                    request.getPreparedByRole(),
                    request.getAcknowledgedByName(),
                    request.getAcknowledgedByRole(),
                    request.getReviewedByName(),
                    request.getReviewedByRole()
            );
            if (success) {
                return ResponseEntity.ok("Approval updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Approval not found with department ID: " + department_id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Approval: " + e.getMessage());
        }
    }

    

}

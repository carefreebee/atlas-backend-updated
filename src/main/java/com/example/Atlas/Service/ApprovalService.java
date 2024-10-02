package com.example.Atlas.Service;


import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.ApprovalEntity;
import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Repository.ApprovalRepository;
import com.example.Atlas.Repository.DepartmentRepository;

@Service
public class ApprovalService {

    @Autowired
    ApprovalRepository arepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public ApprovalEntity insertApproval(ApprovalEntity request) {
        if (request.getDepartment() == null) {
            throw new IllegalArgumentException("Department cannot be null"); 
        }
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department); // Set the department here
        return arepo.save(request);
    }

    
     public List<ApprovalEntity> getApprovalByDepartmentId(int departmentId) {
        return arepo.findByDepartmentId(departmentId);
    }


    public boolean updateApprovalDetailsByDepartmentId(
        int department_id, 
        String preparedByName,
        String preparedByRole,
        String acknowledgedByName,
        String acknowledgedByRole,
        String reviewedByName,
        String reviewedByRole
) {
    try {
        List<ApprovalEntity> approvalList = arepo.findByDepartmentId(department_id); // Assuming you have a findByDepartmentId method in arepo
        if (!approvalList.isEmpty()) {
            for (ApprovalEntity approval : approvalList) { 
                // Update the approval details for each entity with matching department_id
                approval.setAcknowledgedByName(acknowledgedByName);
                approval.setPreparedByName(preparedByName);
                approval.setPreparedByRole(preparedByRole);
                approval.setAcknowledgedByRole(acknowledgedByRole);
                approval.setReviewedByName(reviewedByName);
                approval.setReviewedByRole(reviewedByRole);
            }

            // Save the updated entities back to the database
            arepo.saveAll(approvalList); // Assuming you have a saveAll method in arepo
            return true; // Approval details successfully updated
        } else {
            return false; // No approval found with the given department_id
        }
    } catch (Exception e) {
        throw new RuntimeException("An error occurred while updating approval details.", e);
    }
}
}

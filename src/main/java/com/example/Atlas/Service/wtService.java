package com.example.Atlas.Service;
import java.util.NoSuchElementException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.wtRepository;
import com.example.Atlas.Entity.wtEntity;

@Service
public class wtService {
    @Autowired
    wtRepository wtrepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public wtEntity insertWtStrat(wtEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return wtrepo.save(request);
    }
    
     public List<wtEntity> getWtStratByDepartmentIdNonSorted(int departmentId) {
        return wtrepo.findByDepartmentIdAndSorted(departmentId);
    }

    public List<wtEntity> getWtStratByDepartmentId(int departmentId) {
        return wtrepo.findByDepartmentId(departmentId);
    }

    public String deleteWt(int wtId) {
        String msg="";
        if (wtrepo.findById(wtId) != null) {
            wtrepo.deleteById(wtId);
            msg = "W-T "+wtId+" is succesfully deleted!";
        } else 
            msg = "W-T "+wtId+" does not exist!";
            return msg;
    } 

    public wtEntity updateSortedStatus(int id) {
        wtEntity wt = wtrepo.findById(id).orElse(null);
        if (wt != null) {
            wt.setSorted(1); // Flip to 1
            return wtrepo.save(wt); 
        }
        return null;
    }
    
}

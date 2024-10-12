package com.example.Atlas.Service;

import java.util.NoSuchElementException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.woRepository;
import com.example.Atlas.Entity.woEntity;

@Service
public class woService {
    @Autowired
    woRepository worepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public woEntity insertWoStrat(woEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return worepo.save(request);
    }
    
     public List<woEntity> getWoStratByDepartmentIdNonSorted(int departmentId) {
        return worepo.findByDepartmentIdAndSorted(departmentId);
    }

    public List<woEntity> getWoStratByDepartmentId(int departmentId) {
        return worepo.findByDepartmentId(departmentId);
    }

    public String deleteWo(int woId) {
        String msg="";
        if (worepo.findById(woId) != null) {
            worepo.deleteById(woId);
            msg = "W-O "+woId+" is succesfully deleted!";
        } else 
            msg = "W-O "+woId+" does not exist!";
            return msg;
    } 

        public woEntity updateSortedStatus(int id) {
        woEntity wo = worepo.findById(id).orElse(null);
        if (wo != null) {
            wo.setSorted(1); // Flip to 1
            return worepo.save(wo); 
        }
        return null;
    }
    
}

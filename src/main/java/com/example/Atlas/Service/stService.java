package com.example.Atlas.Service;

import java.util.NoSuchElementException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.stRepository;
import com.example.Atlas.Entity.stEntity;

@Service
public class stService {
    @Autowired
    stRepository strepo;

    @Autowired
    DepartmentRepository departmentrepo;

    public stEntity insertStStrat(stEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return strepo.save(request);
    }
    
    public List<stEntity> getStStratByDepartmentIdNonSorted(int departmentId) {
        return strepo.findByDepartmentIdAndSorted(departmentId);
    }

    public List<stEntity> getStStratByDepartmentId(int departmentId) {
        return strepo.findByDepartmentId(departmentId);
    }

    public String deleteSt(int stId) {
        String msg="";
        if (strepo.findById(stId) != null) {
            strepo.deleteById(stId);
            msg = "S-T "+stId+" is succesfully deleted!";
        } else 
            msg = "S-T "+stId+" does not exist!";
            return msg;
    } 

    public stEntity updateSortedStatus(int id) {
        stEntity st = strepo.findById(id).orElse(null);
        if (st != null) {
            st.setSorted(1); // Flip to 1
            return strepo.save(st); 
        }
        return null;
    }

    
}

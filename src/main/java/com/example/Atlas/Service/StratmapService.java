package com.example.Atlas.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.primaryFinancialEntity;
import com.example.Atlas.Entity.FinancialEntity;
import com.example.Atlas.Entity.StakeholderEntity;
import com.example.Atlas.Entity.primaryStakeholderEntity;
import com.example.Atlas.Entity.LearningEntity;
import com.example.Atlas.Entity.primaryLearningEntity;
import com.example.Atlas.Entity.InternalEntity;
import com.example.Atlas.Entity.primaryInternalEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.FinancialRepository;
import com.example.Atlas.Repository.InternalRepository;
import com.example.Atlas.Repository.primaryInternalRepository;
import com.example.Atlas.Repository.primaryLearningRepository;
import com.example.Atlas.Repository.LearningRepository;
import com.example.Atlas.Repository.StakeholderRepository;
import com.example.Atlas.Repository.primaryFinancialRepository;
import com.example.Atlas.Repository.primaryStakeholderRepository;

@Service
public class StratmapService {
    @Autowired
    FinancialRepository financialrepo;

    @Autowired
    StakeholderRepository stakeholderrepo;

    @Autowired
    LearningRepository learningrepo;

    @Autowired
    InternalRepository internalrepo;

    @Autowired
    DepartmentRepository departmentrepo;

    @Autowired
    primaryFinancialRepository primaryfinancialrepo;

    @Autowired
    primaryStakeholderRepository primarystakeholderrepo;

    @Autowired
    primaryInternalRepository primaryinternalrepo;

    @Autowired
    primaryLearningRepository primarylearningrepo;

    @Autowired
    private EntityManager entityManager;



    public Map<String, Object> getStrategiesByDepartmentId(int departmentId) {
        Map<String, Object> strategies = new HashMap<>();
        strategies.put("financial", financialrepo.findByDepartmentId(departmentId));
        strategies.put("stakeholder", stakeholderrepo.findByDepartmentId(departmentId));
        strategies.put("internalProcess", internalrepo.findByDepartmentId(departmentId));
        strategies.put("learningGrowth", learningrepo.findByDepartmentId(departmentId));
        return strategies;
    }

    public FinancialEntity insertFinancial(FinancialEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return financialrepo.save(request);
    }
    
     public List<FinancialEntity> getFinancialByDepartmentId(int departmentId) {
        return financialrepo.findByDepartmentId(departmentId);
    }

    public FinancialEntity updateFinancialById(int id, String input, int departmentId) {
        Optional<FinancialEntity> optionalFinancial = financialrepo.findById(id);
        if (optionalFinancial.isPresent()) {
            FinancialEntity existingFinancial = optionalFinancial.get();
            Optional<DepartmentEntity> optionalDepartment = departmentrepo.findById(departmentId);
            if (optionalDepartment.isPresent()) {
                DepartmentEntity department = optionalDepartment.get();
                existingFinancial.setDepartment(department);
            } else {
                throw new NoSuchElementException("Department not found with ID: " + departmentId);
            }
            return financialrepo.save(existingFinancial);
        } else {
            throw new NoSuchElementException("Financial strategy not found with ID: " + id);
        }
    }

    

    public String deleteFinancial(int financialId) {
        String msg="";
        if (financialrepo.findById(financialId) != null) {
            financialrepo.deleteById(financialId);
            msg = "Strength "+financialId+" is succesfully deleted!";
        } else 
            msg = "Strength "+financialId+" does not exist!";
            return msg;
    } 

    public FinancialEntity editFinancialEntity(int id, FinancialEntity financialEntity) {
        // Find the existing financial entity by its ID
        FinancialEntity existingEntity = financialrepo.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Financial entity not found with id: " + id));

        existingEntity.setOffice_target(financialEntity.getOffice_target());
        existingEntity.setDepartment(financialEntity.getDepartment());
    
        // Save the updated entity to the database
        return financialrepo.save(existingEntity);
    }

    public primaryFinancialEntity insertPrimaryFinancial(primaryFinancialEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return primaryfinancialrepo.save(request);
    }
    
    public List<primaryFinancialEntity> getPrimaryFinancialByDepartmentId(int departmentId) {
        return primaryfinancialrepo.findByDepartmentId(departmentId);
    }

    public StakeholderEntity insertStakeholder(StakeholderEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return stakeholderrepo.save(request);
    }

    public List<StakeholderEntity> getStakeholderByDepartmentId(int departmentId) {
        return stakeholderrepo.findByDepartmentId(departmentId);
    }

    public String deleteStakeholder(int stakeholderId) {
        String msg="";
        if (stakeholderrepo.findById(stakeholderId) != null) {
            stakeholderrepo.deleteById(stakeholderId);
            msg = "Stakeholder "+stakeholderId+" is succesfully deleted!";
        } else 
            msg = "Stakeholder "+stakeholderId+" does not exist!";
            return msg;
    } 
    
    public StakeholderEntity editStakeholderEntity(int id, StakeholderEntity stakeholderEntity) {
        // Find the existing financial entity by its ID
        StakeholderEntity existingEntity = stakeholderrepo.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Stakeholder entity not found with id: " + id));

        existingEntity.setOffice_target(stakeholderEntity.getOffice_target());
        existingEntity.setDepartment(stakeholderEntity.getDepartment());
    
        // Save the updated entity to the database
        return stakeholderrepo.save(existingEntity);
    }

    public primaryStakeholderEntity insertPrimaryStakeholder(primaryStakeholderEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return primarystakeholderrepo.save(request);
    }

    public List<primaryStakeholderEntity> getPrimaryStakeholderByDepartmentId(int departmentId) {
        return primarystakeholderrepo.findByDepartmentId(departmentId);
    }
    
    public LearningEntity insertLearning (LearningEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return learningrepo.save(request);
    }

    public List<LearningEntity> getLearningByDepartmentId(int departmentId) {
        return learningrepo.findByDepartmentId(departmentId);
    } 

    public LearningEntity editLearningEntity(int id, LearningEntity learningEntity) {
        // Find the existing financial entity by its ID
        LearningEntity existingEntity = learningrepo.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Learning entity not found with id: " + id));

        existingEntity.setOffice_target(learningEntity.getOffice_target());
        existingEntity.setDepartment(learningEntity.getDepartment());
    
        // Save the updated entity to the database
        return learningrepo.save(existingEntity);
    }

    public String deleteLearning(int learningId) {
        String msg="";
        if (learningrepo.findById(learningId) != null) {
            learningrepo.deleteById(learningId);
            msg = "Learning "+learningId+" is succesfully deleted!";
        } else 
            msg = "Learning "+learningId+" does not exist!";
            return msg;
    }
    
    public primaryLearningEntity insertPrimaryLearning(primaryLearningEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return primarylearningrepo.save(request);
    }

    public List<primaryLearningEntity> getPrimaryLearningByDepartmentId(int departmentId) {
        return primarylearningrepo.findByDepartmentId(departmentId);
    }

    public InternalEntity insertInternal (InternalEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return internalrepo.save(request);
    }

    public List<InternalEntity> getInternalByDepartmentId(int departmentId) {
        return internalrepo.findByDepartmentId(departmentId);
    }
   
    
    public InternalEntity editInternalEntity(int id, InternalEntity internalEntity) {
        // Find the existing financial entity by its ID
        InternalEntity existingEntity = internalrepo.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Internal entity not found with id: " + id));

        existingEntity.setOffice_target(internalEntity.getOffice_target());
        existingEntity.setDepartment(internalEntity.getDepartment());
    
        // Save the updated entity to the database
        return internalrepo.save(existingEntity);
    }
    
    public String deleteInternal(int internalId) {
        String msg="";
        if (internalrepo.findById(internalId) != null) {
            internalrepo.deleteById(internalId);
            msg = "Learning "+internalId+" is succesfully deleted!";
        } else 
            msg = "Learning "+internalId+" does not exist!";
            return msg;
    }

    public List<primaryInternalEntity> getPrimaryInternalByDepartmentId(int departmentId) {
        return primaryinternalrepo.findByDepartmentId(departmentId);
    }

    public primaryInternalEntity insertPrimaryInternal(primaryInternalEntity request) {
        DepartmentEntity department = departmentrepo.findById(request.getDepartment().getId()).orElseThrow(() -> new NoSuchElementException("Department not found"));
        request.setDepartment(department);
        return primaryinternalrepo.save(request);
    }

    @Transactional
    public void financialClearTable() {
        // 1. DELETE FROM where user_generated = 0
        entityManager.createNativeQuery("DELETE FROM financial_bsc WHERE user_generated = 0").executeUpdate();

        // 2. Reset Auto-Increment (MySQL Example) - Only if rows were deleted
        int deletedRows = entityManager.createNativeQuery("SELECT ROW_COUNT()").getFirstResult();
        if (deletedRows > 0) {
            entityManager.createNativeQuery("ALTER TABLE financial_bsc AUTO_INCREMENT = 1").executeUpdate(); 
        }
    }

    @Transactional
    public void stakeholderClearTable() {
        // 1. DELETE FROM where user_generated = 0
        entityManager.createNativeQuery("DELETE FROM stakeholder_bsc WHERE user_generated = 0").executeUpdate();

        // 2. Reset Auto-Increment (MySQL Example) - Only if rows were deleted
        int deletedRows = entityManager.createNativeQuery("SELECT ROW_COUNT()").getFirstResult();
        if (deletedRows > 0) {
            entityManager.createNativeQuery("ALTER TABLE stakeholder_bsc AUTO_INCREMENT = 1").executeUpdate(); 
        }
    }

    @Transactional
    public void learningClearTable() {
        // 1. DELETE FROM where user_generated = 0
        entityManager.createNativeQuery("DELETE FROM learning_bsc WHERE user_generated = 0").executeUpdate();

        // 2. Reset Auto-Increment (MySQL Example) - Only if rows were deleted
        int deletedRows = entityManager.createNativeQuery("SELECT ROW_COUNT()").getFirstResult();
        if (deletedRows > 0) {
            entityManager.createNativeQuery("ALTER TABLE learning_bsc AUTO_INCREMENT = 1").executeUpdate(); 
        }
    }

    @Transactional
    public void internalClearTable() {
        // 1. DELETE FROM where user_generated = 0
        entityManager.createNativeQuery("DELETE FROM internal_bsc WHERE user_generated = 0").executeUpdate();

        // 2. Reset Auto-Increment (MySQL Example) - Only if rows were deleted
        int deletedRows = entityManager.createNativeQuery("SELECT ROW_COUNT()").getFirstResult();
        if (deletedRows > 0) {
            entityManager.createNativeQuery("ALTER TABLE internal_bsc AUTO_INCREMENT = 1").executeUpdate(); 
        }
    }
    
}

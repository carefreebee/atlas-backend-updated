package com.example.Atlas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.DepartmentEntity;
import com.example.Atlas.Entity.FeedbackEntity;
import com.example.Atlas.Repository.DepartmentRepository;
import com.example.Atlas.Repository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public FeedbackEntity saveFeedback(FeedbackEntity feedback, int departmentId) {
        DepartmentEntity department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        feedback.setDepartment(department);
        return feedbackRepository.save(feedback);
    }

    public FeedbackEntity getFeedback(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    public List<FeedbackEntity> getAllFeedbackByDepartment(int departmentId) {
        DepartmentEntity department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return feedbackRepository.findByDepartment(department);
    }
}

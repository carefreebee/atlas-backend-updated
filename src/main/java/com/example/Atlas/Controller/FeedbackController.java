package com.example.Atlas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Atlas.Entity.FeedbackEntity;
import com.example.Atlas.Service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackEntity> createFeedback(@RequestBody FeedbackEntity feedback, @RequestParam int departmentId) {
        System.out.println("Received feedback: " + feedback);
        System.out.println("Department ID: " + departmentId);
        FeedbackEntity savedFeedback = feedbackService.saveFeedback(feedback, departmentId);
        return ResponseEntity.ok(savedFeedback);
    }

    @GetMapping("/getAll/{departmentId}")
    public ResponseEntity<List<FeedbackEntity>> getAllFeedbackByDepartment(@PathVariable int departmentId) {
        List<FeedbackEntity> feedbackList = feedbackService.getAllFeedbackByDepartment(departmentId);
        return ResponseEntity.ok(feedbackList);
    }

}

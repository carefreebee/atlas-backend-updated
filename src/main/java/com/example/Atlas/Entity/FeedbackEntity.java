package com.example.Atlas.Entity;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Feeling feeling;

    @Enumerated(EnumType.STRING)
    private SatisfactionRating friendliness;

    @Enumerated(EnumType.STRING)
    private SatisfactionRating knowledge;

    @Enumerated(EnumType.STRING)
    private SatisfactionRating quickness;

    @Column(columnDefinition = "TEXT")
    private String overallFeedback;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Feeling getFeeling() {
        return feeling;
    }

    public void setFeeling(Feeling feeling) {
        this.feeling = feeling;
    }

    public SatisfactionRating getFriendliness() {
        return friendliness;
    }

    public void setFriendliness(SatisfactionRating friendliness) {
        this.friendliness = friendliness;
    }

    public SatisfactionRating getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(SatisfactionRating knowledge) {
        this.knowledge = knowledge;
    }

    public SatisfactionRating getQuickness() {
        return quickness;
    }

    public void setQuickness(SatisfactionRating quickness) {
        this.quickness = quickness;
    }

    public String getOverallFeedback() {
        return overallFeedback;
    }

    public void setOverallFeedback(String overallFeedback) {
        this.overallFeedback = overallFeedback;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public enum Feeling {
        HAPPY, NEUTRAL, SAD, MAD
    }

    public enum SatisfactionRating {
        VERY_SATISFIED, SATISFIED, NEUTRAL, UNSATISFIED, VERY_UNSATISFIED
    }
}


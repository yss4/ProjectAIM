package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.CourseOutcomeDirectAssessmentBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity(name = "AIM_ENTITY_CODA")
public class CourseOutcomeDirectAssessment implements AIMEntity, Serializable {
    /***************************************************************************
     * Information maintained about each CODA includes:                        *
     * ************************************************************************/
    /* ID: Used to identify the CODA                                          */
    String id;
    
    /* Course Outcome: The assessed Course Outcome                            */
    private CourseOutcome courseOutcome;
    
    /* Assessment Instrument: Coursework used for this assessment (ex: "HW4") */
    private String assessmentInstrument;
    
    /* Rationale: Reasoning behind threshold score and assessment instrument  */
    private String rationale;
    
    /* Threshold Score: Scores above this achieved the course outcome         */
    private int thresholdScore;
    
    /* Attainment Level: Level of students with above-threshold scores        */
    private double attainmentLevel;

    public CourseOutcomeDirectAssessment() {
    }

    public CourseOutcomeDirectAssessment(String primaryKey) {
        id = primaryKey;
    }

    public CourseOutcomeDirectAssessment(CourseOutcomeDirectAssessmentBean courseOutcomeDirectAssessmentBean) {
        id = courseOutcomeDirectAssessmentBean.getId();
        courseOutcome = courseOutcomeDirectAssessmentBean.getCourseOutcome();
        assessmentInstrument = courseOutcomeDirectAssessmentBean.getAssessmentInstrument();
        rationale = courseOutcomeDirectAssessmentBean.getRationale();
        thresholdScore = courseOutcomeDirectAssessmentBean.getThresholdScore();
        attainmentLevel = courseOutcomeDirectAssessmentBean.getAttainmentLevel();
    }

    @Id
    @Column(name = "CODA_ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_CODAS_COURSE_OUTCOMES",
            joinColumns = {
        @JoinColumn(name = "CODA_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "COURSE_OUTCOME_ID")})
    public CourseOutcome getCourseOutcome() {
        return courseOutcome;
    }

    public void setCourseOutcome(CourseOutcome courseOutcome) {
        this.courseOutcome = courseOutcome;
    }

    @Column(name = "CODA_ASSESSMENT_INSTRUMENT")
    public String getAssessmentInstrument() {
        return assessmentInstrument;
    }

    public void setAssessmentInstrument(String assessmentInstrument) {
        this.assessmentInstrument = assessmentInstrument;
    }

    @Column(name = "CODA_RATIONALE")
    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    @Column(name = "CODA_THRESHOLD_SCORE")
    public int getThresholdScore() {
        return thresholdScore;
    }

    public void setThresholdScore(int thresholdScore) {
        this.thresholdScore = thresholdScore;
    }

    @Column(name = "CODA_ATTAINMENT_LEVEL")
    public double getAttainmentLevel() {
        return attainmentLevel;
    }

    public void setAttainmentLevel(double attainmentLevel) {
        this.attainmentLevel = attainmentLevel;
    }

    @Override
    public String toString() {
        return "CourseOutcomeDirectAssessment";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 89 * hash + (this.assessmentInstrument != null ? this.assessmentInstrument.hashCode() : 0);
        hash = 89 * hash + (this.rationale != null ? this.rationale.hashCode() : 0);
        hash = 89 * hash + this.thresholdScore;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CourseOutcomeDirectAssessment other = (CourseOutcomeDirectAssessment) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.assessmentInstrument == null) ? (other.assessmentInstrument != null) : !this.assessmentInstrument.equals(other.assessmentInstrument)) {
            return false;
        }
        if ((this.rationale == null) ? (other.rationale != null) : !this.rationale.equals(other.rationale)) {
            return false;
        }
        if (this.thresholdScore != other.thresholdScore) {
            return false;
        }
        return true;
    }    
}

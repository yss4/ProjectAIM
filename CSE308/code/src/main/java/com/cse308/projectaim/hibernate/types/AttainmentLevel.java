package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "AIM_ENTITY_ATTAINMENT_LEVEL")
public class AttainmentLevel implements Serializable, AIMEntity {

    private int id;
    private double level;
    private CourseOutcome mappedCourseOutcome;
    private CourseOffering mappedCourseOffering;
    private StudentOutcome mappedStudentOutcomeSurveyLevel;
    private StudentOutcome mappedStudentOutcomeDirectLevel;

    public AttainmentLevel() {
    }

    @Id
    @Column(name = "ATTAINMENT_LEVEL_ID")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ATTAINMENT_LEVEL_LEVEL")
    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_ATTAINMENT_LEVELS_COURSE_OUTCOMES",
            joinColumns = {
        @JoinColumn(name = "ATTAINMENT_LEVEL_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "COURSE_OUTCOME_ID")})
    public CourseOutcome getMappedCourseOutcome() {
        return mappedCourseOutcome;
    }

    public void setMappedCourseOutcome(CourseOutcome mappedCourseOutcome) {
        this.mappedCourseOutcome = mappedCourseOutcome;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_ATTAINMENT_LEVELS_COURSE_OFFERINGS",
            joinColumns = {
        @JoinColumn(name = "ATTAINMENT_LEVEL_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "COURSE_OFFERING_SECTION_NUMBER")})
    public CourseOffering getMappedCourseOffering() {
        return mappedCourseOffering;
    }

    public void setMappedCourseOffering(CourseOffering mappedCourseOffering) {
        this.mappedCourseOffering = mappedCourseOffering;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_ATTAINMENT_LEVELS_STUDENT_OUTCOME_SURVEY_ATTAINMENT_LEVELS",
            joinColumns = {
        @JoinColumn(name = "ATTAINMENT_LEVEL_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "STUDENT_OUTCOME_ID")})
    public StudentOutcome getMappedStudentOutcomeSurveyLevel() {
        return mappedStudentOutcomeSurveyLevel;
    }

    public void setMappedStudentOutcomeSurveyLevel(StudentOutcome mappedStudentOutcomeSurveyLevel) {
        this.mappedStudentOutcomeSurveyLevel = mappedStudentOutcomeSurveyLevel;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_ATTAINMENT_LEVELS_STUDENT_OUTCOME_DIRECT_ATTAINMENT_LEVELS",
            joinColumns = {
        @JoinColumn(name = "ATTAINMENT_LEVEL_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "STUDENT_OUTCOME_ID")})
    public StudentOutcome getMappedStudentOutcomeDirectLevel() {
        return mappedStudentOutcomeDirectLevel;
    }

    public void setMappedStudentOutcomeDirectLevel(StudentOutcome mappedStudentOutcomeDirectLevel) {
        this.mappedStudentOutcomeDirectLevel = mappedStudentOutcomeDirectLevel;
    }

    @Override
    public String toString() {
        return "AttainmentLevel";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.level) ^ (Double.doubleToLongBits(this.level) >>> 32));
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
        final AttainmentLevel other = (AttainmentLevel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.level) != Double.doubleToLongBits(other.level)) {
            return false;
        }
        return true;
    }    
}

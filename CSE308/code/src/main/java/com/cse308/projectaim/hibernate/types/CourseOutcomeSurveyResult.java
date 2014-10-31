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
import javax.persistence.OneToOne;

@Entity(name = "AIM_ENTITY_SURVEY_RESULT")
public class CourseOutcomeSurveyResult implements Serializable, AIMEntity {
    /***************************************************************************
     * Information maintained about each Course Outcome Survey Result includes:*
     **************************************************************************/
    /* ID: Used to identify the result                                        */
    private int id;
    
    /* CourseOutcome on which the survey was performed                        */
    private CourseOutcome courseOutcome;
    
    /* Average rating of attainment of the course outcome                     */
    private double averageRating;

    public CourseOutcomeSurveyResult() {
    }

    @Id
    @Column(name = "SURVEY_RESULT_ID")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "SURVEY_RESULT_AVERAGE_RATING")
    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_SURVEY_RESULTS_COURSE_OUTCOMES",
            joinColumns = {
        @JoinColumn(name = "SURVEY_RESULT_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "COURSE_OUTCOME_ID")})
    public CourseOutcome getMappedCourseOutcome() {
        return courseOutcome;
    }

    public void setMappedCourseOutcome(CourseOutcome mappedCourseOutcome) {
        this.courseOutcome = mappedCourseOutcome;
    }

    @Override
    public String toString() {
        return "SurveyResult";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.averageRating) ^ (Double.doubleToLongBits(this.averageRating) >>> 32));
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
        final CourseOutcomeSurveyResult other = (CourseOutcomeSurveyResult) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.averageRating) != Double.doubleToLongBits(other.averageRating)) {
            return false;
        }
        return true;
    }    
}

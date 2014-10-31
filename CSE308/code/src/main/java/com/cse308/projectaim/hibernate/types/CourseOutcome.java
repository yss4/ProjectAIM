package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.CourseOutcomeBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "AIM_ENTITY_COURSE_OUTCOME")
public class CourseOutcome implements AIMEntity, Serializable {
    /***************************************************************************
     * Information maintained about each CourseOutcome includes:               *
     **************************************************************************/
    /* Sequence Number                                                        */
    private Integer id;
    
    /* Description                                                            */
    private String description;
    
    /* Student Outcome: Student Outcome enabled by this Course Outcome        */
    private StudentOutcome studentOutcome;
    
    /* Rationale: Explanation of why this maps to the Student Outcome         */
    private String rationale;
    
    /* Assessed: Boolean determining if used to assess the Student Outcome    */
    private Boolean assessed;

    public CourseOutcome() {
    }

    public CourseOutcome(Integer primaryKey) {
        id = primaryKey;
    }

    public CourseOutcome(CourseOutcomeBean courseOutcomeBean) {
        id = courseOutcomeBean.getId();
        description = courseOutcomeBean.getDescription();
        studentOutcome = courseOutcomeBean.getStudentOutcome();
        rationale = courseOutcomeBean.getRationale();
        assessed = courseOutcomeBean.getAssessed();
    }

    @Id
    @GeneratedValue()
    @Column(name = "COURSE_OUTCOME_ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "COURSE_OUTCOME_DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_STUDENT_OUTCOMES_COURSE_OUTCOMES",
            joinColumns = {
        @JoinColumn(name = "COURSE_OUTCOME_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "STUDENT_OUTCOME_ID")})
    public StudentOutcome getStudentOutcome() {
        return studentOutcome;
    }

    public void setStudentOutcome(StudentOutcome studentOutcome) {
        this.studentOutcome = studentOutcome;
    }

    @Column(name = "COURSE_OUTCOME_RATIONALE")
    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    @Column(name = "COURSE_OUTCOME_ASSESSED_STATUS")
    public Boolean getAssessed() {
        return assessed;
    }

    public void setAssessed(Boolean assessed) {
        this.assessed = assessed;
    }

    @Override
    public String toString() {
        return "CourseOutcome";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.rationale);
        hash = 67 * hash + Objects.hashCode(this.assessed);
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
        final CourseOutcome other = (CourseOutcome) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.rationale, other.rationale)) {
            return false;
        }
        if (!Objects.equals(this.assessed, other.assessed)) {
            return false;
        }
        return true;
    }
}

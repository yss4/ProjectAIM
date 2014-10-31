package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.StudentOutcomeBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "AIM_ENTITY_STUDENT_OUTCOME")
public class StudentOutcome implements AIMEntity, Serializable {
    /***************************************************************************
     * Information maintained about each StudentOutcome includes:              *
     **************************************************************************/
    /* ID: User-Specified ID for the Student Outcome (ex: "Ethics")           */
    private String studentOutcomeId;
    
    /* Sequence Number: number used to order student outcomes                 */
    private Integer sequence;
    
    /* Short Name: Used to identify the student outcome in column headings    */
    private String shortName;
    
    /* Description */
    private String description;
    
    /* Target Direct-Assessment Attainment Level for each semester            */
    private Set<AttainmentLevel> directAssessmentAttainmentLevel;
    
    /* Target Survey-Assessment Attainment Level for each semester            */
    private Set<AttainmentLevel> surveyAssessmentAttainmentLevel;
    
   /***************************************************************************
     * Bi-Directional mappings from other AIMEntities:                         *
     **************************************************************************/
    private DegreeProgram degreeProgram;
    private Set<CourseOutcome> courseOutcomes;

    public StudentOutcome() {
        courseOutcomes = new HashSet<CourseOutcome>();
        directAssessmentAttainmentLevel = new HashSet<AttainmentLevel>();
        surveyAssessmentAttainmentLevel = new HashSet<AttainmentLevel>();                
    }

    public StudentOutcome(String primaryKey) {
        studentOutcomeId = primaryKey;
    }

    public StudentOutcome(StudentOutcomeBean studentOutcomeBean) {
        studentOutcomeId = studentOutcomeBean.getStudentOutcomeId();
        sequence = studentOutcomeBean.getSequence();
        shortName = studentOutcomeBean.getShortName();
        description = studentOutcomeBean.getDescription();
        degreeProgram = studentOutcomeBean.getDegreeProgram();
        courseOutcomes = studentOutcomeBean.getCourseOutcomes();
    }

    @Id
    @Column(name = "STUDENT_OUTCOME_ID")
    public String getStudentOutcomeId() {
        return studentOutcomeId;
    }

    public void setStudentOutcomeId(String studentOutcomeId) {
        this.studentOutcomeId = studentOutcomeId;
    }

    @Column(name = "STUDENT_OUTCOME_SEQUENCE")
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Column(name = "STUDENT_OUTCOME_SHORT_NAME")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Column(name = "STUDENT_OUTCOME_DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_STUDENT_OUTCOMES_DEGREE_PROGRAMS",
            joinColumns = {
        @JoinColumn(name = "STUDENT_OUTCOME_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "DEGREE_PROGRAM_ID")})
    public DegreeProgram getDegreeProgram() {
        return degreeProgram;
    }

    public void setDegreeProgram(DegreeProgram degreeProgram) {
        this.degreeProgram = degreeProgram;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_STUDENT_OUTCOMES_COURSE_OUTCOMES",
            joinColumns = {
        @JoinColumn(name = "STUDENT_OUTCOME_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "COURSE_OUTCOME_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<CourseOutcome> getCourseOutcomes() {
        return courseOutcomes;
    }

    public void setCourseOutcomes(Set<CourseOutcome> courseOutcomes) {
        this.courseOutcomes = courseOutcomes;
    }

    @OneToMany(fetch = FetchType.EAGER)
     @JoinTable(name = "JOIN_ATTAINMENT_LEVELS_STUDENT_OUTCOME_DIRECT_ATTAINMENT_LEVELS",
            joinColumns = {
        @JoinColumn(name = "STUDENT_OUTCOME_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "ATTAINMENT_LEVEL_ID")})
    public Set<AttainmentLevel> getDirectAssessmentAttainmentLevel() {
        return directAssessmentAttainmentLevel;
    }

    public void setDirectAssessmentAttainmentLevel(Set<AttainmentLevel> directAssessmentAttainmentLevel) {
        this.directAssessmentAttainmentLevel = directAssessmentAttainmentLevel;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_ATTAINMENT_LEVELS_STUDENT_OUTCOME_SURVEY_ATTAINMENT_LEVELS",
            joinColumns = {
        @JoinColumn(name = "STUDENT_OUTCOME_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "ATTAINMENT_LEVEL_ID")})
    public Set<AttainmentLevel> getSurveyAssessmentAttainmentLevel() {
        return surveyAssessmentAttainmentLevel;
    }

    public void setSurveyAssessmentAttainmentLevel(Set<AttainmentLevel> surveyAssessmentAttainmentLevel) {
        this.surveyAssessmentAttainmentLevel = surveyAssessmentAttainmentLevel;
    }
    
    @Override
    public String toString() {
        return "StudentOutcome";
    }

    @Override
    public Serializable primaryKey() {
        return this.studentOutcomeId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.studentOutcomeId != null ? this.studentOutcomeId.hashCode() : 0);
        hash = 47 * hash + (this.sequence != null ? this.sequence.hashCode() : 0);
        hash = 47 * hash + (this.shortName != null ? this.shortName.hashCode() : 0);
        hash = 47 * hash + (this.description != null ? this.description.hashCode() : 0);
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
        final StudentOutcome other = (StudentOutcome) obj;
        if ((this.studentOutcomeId == null) ? (other.studentOutcomeId != null) : !this.studentOutcomeId.equals(other.studentOutcomeId)) {
            return false;
        }
        if (this.sequence != other.sequence && (this.sequence == null || !this.sequence.equals(other.sequence))) {
            return false;
        }
        if ((this.shortName == null) ? (other.shortName != null) : !this.shortName.equals(other.shortName)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        return true;
    }
}

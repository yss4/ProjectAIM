package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.SurveyBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "AIM_ENTITY_SURVEY")
public class Survey implements AIMEntity, Serializable {
    /***************************************************************************
     * Information maintained about each Survey includes:                      *
     **************************************************************************/
    /* ID: Used to identify the result                                        */
    private int id;

    /* Semester: Semester in which the survey was conducted                   */
    private Semester semester;

    /* Degree Programs: The relevant Degree Programs                          */
    private Set<DegreeProgram> degreePrograms = new HashSet<DegreeProgram>();
    
    /* Group: Surveyed Group (ex: "Alumni", "Employers", "Graduate students") */
    private String surveyGroup;
    
    /* Initiator: Organizational Unit responsible for the survey              */
    private String initiator;
    
    /* Results: File containing the survey results                            */
    private AIMFileWrapper results;
    
    /* PEO attainment levels: average attainment level of each PEO, according to this survey.   */
    private Set<PEOAttainmentLevel> attainmentLevels;

    public Survey() {
		attainmentLevels = new HashSet<PEOAttainmentLevel>();
    }

    public Survey(SurveyBean surveyBean) {
        id = surveyBean.getId();
        semester = surveyBean.getSemester();
        degreePrograms = surveyBean.getDegreePrograms();
        surveyGroup = surveyBean.getSurveyGroup();
        initiator = surveyBean.getInitiator();
        results = surveyBean.getResults();
		attainmentLevels = surveyBean.getAttainmentLevels();
    }

    @Id
    @Column(name = "SURVEY_ID")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_SURVEYS_SEMESTERS",
            joinColumns = {
        @JoinColumn(name = "SURVEY_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "SEMESTER_TERM"),
        @JoinColumn(name = "SEMESTER_YEAR")
    })
    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_SURVEYS_DEGREE_PROGRAMS",
            joinColumns = {
        @JoinColumn(name = "SURVEY_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "DEGREE_PROGRAM_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<DegreeProgram> getDegreePrograms() {
        return degreePrograms;
    }

    public void setDegreePrograms(Set<DegreeProgram> degreePrograms) {
        this.degreePrograms = degreePrograms;
    }

    @Column(name = "SURVEY_GROUP")
    public String getSurveyGroup() {
        return surveyGroup;
    }

    public void setSurveyGroup(String surveyGroup) {
        this.surveyGroup = surveyGroup;
    }

    @Column(name = "SURVEY_INITIATOR")
    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_SURVEYS_RESULTS",
            joinColumns = {
        @JoinColumn(name = "SURVEY_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "FILE_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public AIMFileWrapper getResults() {
        return results;
    }

    public void setResults(AIMFileWrapper results) {
        this.results = results;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_SURVEYS_PEO_ATTAINMENT_LEVEL",
            joinColumns = {
        @JoinColumn(name = "SURVEY_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "PEO_ATTAINMENT_LEVEL_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<PEOAttainmentLevel> getAttainmentLevels() {
        return attainmentLevels;
    }

    public void setAttainmentLevels(Set<PEOAttainmentLevel> attainmentLevels) {
        this.attainmentLevels = attainmentLevels;
    }
    
    @Override
    public String toString() {
        return "Survey";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }
}

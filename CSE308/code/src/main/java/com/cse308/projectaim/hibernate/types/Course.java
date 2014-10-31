package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.CourseBean;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "AIM_ENTITY_COURSE")
public class Course implements AIMEntity, Serializable {
    /***************************************************************************
     * Information maintained about each StudentOutcome includes:              *
     **************************************************************************/
    /* Course ID: Standard Course ID (ex: "MAT101")                           */
    private String id;
    
    /* Course Name: Name of this course (ex: "Algebra I")                     */
    private String name;
    
    /* Degree Programs: Degree Programs whose curriculum this course belongs  */
    private Set<DegreeProgram> degreePrograms;
    
    /* Primary Course Coordinator: User who oversees/manages this course      */
    private User primaryCourseCoordinator;

    /* Alternate Course Coordinators who can substitute for the primary       */
    private Set<User> alternateCourseCoordinator;
    
    /* Course Outcomes: Course Outcomes for this course                       */
    private Set<CourseOutcome> courseOutcomes;
    
    public Course() {
        degreePrograms = new HashSet<DegreeProgram>();
        courseOutcomes = new HashSet<CourseOutcome>();
        alternateCourseCoordinator = new HashSet<User>();
    }

    public Course(String primaryKey) {
        id = primaryKey;
    }

    public Course(CourseBean courseBean) {
        id = courseBean.getId();
        name = courseBean.getName();
        degreePrograms = courseBean.getDegreePrograms();
        courseOutcomes = courseBean.getCourseOutcomes();
        alternateCourseCoordinator = courseBean.getAlternateCourseCoordinator();
        primaryCourseCoordinator = courseBean.getPrimaryCourseCoordinator();
    }

    @Id
    @Column(name = "COURSE_ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "COURSE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSES_DEGREE_PROGRAMS",
            joinColumns = {
        @JoinColumn(name = "COURSE_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "DEGREE_PROGRAM_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<DegreeProgram> getDegreePrograms() {
        return degreePrograms;
    }

    public void setDegreePrograms(Set<DegreeProgram> degreePrograms) {
        this.degreePrograms = degreePrograms;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSES_COURSE_OUTCOMES",
            joinColumns = {
        @JoinColumn(name = "COURSE_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "COURSE_OUTCOME_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<CourseOutcome> getCourseOutcomes() {
        return courseOutcomes;
    }

    public void setCourseOutcomes(Set<CourseOutcome> courseOutcomes) {
        this.courseOutcomes = courseOutcomes;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSES_ALTERNATE_COURSE_COORDINATORS",
            joinColumns = {
        @JoinColumn(name = "COURSE_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "USER_USERNAME")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<User> getAlternateCourseCoordinator() {
        return alternateCourseCoordinator;
    }

    public void setAlternateCourseCoordinator(Set<User> alternateCourseCoordinator) {
        this.alternateCourseCoordinator = alternateCourseCoordinator;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSES_PRIMARY_COURSE_COORDINATORS",
            joinColumns = {
        @JoinColumn(name = "COURSE_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "USER_USERNAME")})
    public User getPrimaryCourseCoordinator() {
        return primaryCourseCoordinator;
    }

    public void setPrimaryCourseCoordinator(User primaryCourseCoordinator) {
        this.primaryCourseCoordinator = primaryCourseCoordinator;
    }

    @Override
    public String toString() {
        return "Course";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 11 * hash + (this.name != null ? this.name.hashCode() : 0);
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
        final Course other = (Course) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }    
}

package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.DegreeProgramBean;
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
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "AIM_ENTITY_DEGREE_PROGRAM")
public class DegreeProgram implements AIMEntity, Serializable {
    /***************************************************************************
     * Information maintained about each Degree Program includes:              *
     **************************************************************************/
    /* ID: User-Specified ID for the degree program (e.g., "BS in CHE")       */
    private String degreeProgramId;
    
    /* Description: Description (ex: "Bachelor of Science in Chemistry")      */
    private String description;
    
    /* Department: Department offering this degree program (ex: CEAS)         */
    private String department;
    
    /* Program Educational Objectives (PEOs) */
    private Set<PEO> PEOs;
    
    /* Student Outcomes */
    private Set<StudentOutcome> studentOutcomes;

    /***************************************************************************
     * Bi-Directional mappings from other AIMEntities:                         *
     **************************************************************************/
    private Set<Course> courses;
    private Set<User> users = new HashSet();

    public DegreeProgram() {
        courses = new HashSet();
        PEOs = new HashSet();
        studentOutcomes = new HashSet();
    }

    public DegreeProgram(String primaryKey) {
        degreeProgramId = primaryKey;
    }

    public DegreeProgram(DegreeProgramBean degreeProgramBean) {
        degreeProgramId = degreeProgramBean.getDegreeProgramId();
        description = degreeProgramBean.getDescription();
        department = degreeProgramBean.getDepartment();
        PEOs = degreeProgramBean.getPEOs();
        studentOutcomes = degreeProgramBean.getStudentOutcomes();
        users = degreeProgramBean.getUsers();
    }

    @Id
    @Column(name = "DEGREE_PROGRAM_ID")
    public String getDegreeProgramId() {
        return degreeProgramId;
    }

    public void setDegreeProgramId(String degreeProgramId) {
        this.degreeProgramId = degreeProgramId;
    }

    @Column(name = "DEGREE_PROGRAM_DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "DEGREE_PROGRAM_DEPARTMENT")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_DEGREE_PROGRAMS_PEOS",
            joinColumns = {
        @JoinColumn(name = "DEGREE_PROGRAM_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "PEO_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<PEO> getPEOs() {
        return PEOs;
    }

    public void setPEOs(Set<PEO> PEOs) {
        this.PEOs = PEOs;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_DEGREE_PROGRAMS_STUDENT_OUTCOMES",
            joinColumns = {
        @JoinColumn(name = "DEGREE_PROGRAM_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "STUDENT_OUTCOME_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<StudentOutcome> getStudentOutcomes() {
        return studentOutcomes;
    }

    public void setStudentOutcomes(Set<StudentOutcome> studentOutcomes) {
        this.studentOutcomes = studentOutcomes;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_COURSES_DEGREE_PROGRAMS",
            joinColumns = {
        @JoinColumn(name = "DEGREE_PROGRAM_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "COURSE_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_DEGREE_PROGRAMS_USERS",
            joinColumns = {
        @JoinColumn(name = "DEGREE_PROGRAM_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "USER_USERNAME")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "DegreeProgram";
    }

    @Override
    public Serializable primaryKey() {
        return this.degreeProgramId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.degreeProgramId != null ? this.degreeProgramId.hashCode() : 0);
        hash = 17 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 17 * hash + (this.department != null ? this.department.hashCode() : 0);
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
        final DegreeProgram other = (DegreeProgram) obj;
        if ((this.degreeProgramId == null) ? (other.degreeProgramId != null) : !this.degreeProgramId.equals(other.degreeProgramId)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if ((this.department == null) ? (other.department != null) : !this.department.equals(other.department)) {
            return false;
        }
        if (this.PEOs != other.PEOs && (this.PEOs == null || !this.PEOs.equals(other.PEOs))) {
            return false;
        }
        if (this.studentOutcomes != other.studentOutcomes && (this.studentOutcomes == null || !this.studentOutcomes.equals(other.studentOutcomes))) {
            return false;
        }
        if (this.users != other.users && (this.users == null || !this.users.equals(other.users))) {
            return false;
        }
        return true;
    }
}
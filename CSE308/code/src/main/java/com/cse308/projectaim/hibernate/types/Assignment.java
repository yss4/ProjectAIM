package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.AssignmentBean;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "AIM_ENTITY_ASSIGNMENT")
public class Assignment implements Serializable, AIMEntity {
    /***************************************************************************
     * Information maintained about each Assignment includes:                  *
     * ************************************************************************/
    /* Name: String identifying the assignment (ex: "CSE308 - Homework 4")    */
    private String name;

    /* Description: File containing details of the assignment                 */
    private AIMFileWrapper description;
    
    /* Student Samples: Samples that students submitted for this assignment   */
    private Set<StudentSample> samples;

    public Assignment() {
        samples = new HashSet<StudentSample>();
    }
    
    public Assignment(AssignmentBean assignmentBean) {
        name = assignmentBean.getName();
        description = assignmentBean.getDescription();
        samples = assignmentBean.getSamples();
    }

    @Id
    @Column(name = "ASSIGNMENT_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_ASSIGNMENTS_FILES",
            joinColumns = {
        @JoinColumn(name = "ASSIGNMENT_NAME")},
            inverseJoinColumns = {
        @JoinColumn(name = "FILE_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public AIMFileWrapper getDescription() {
        return description;
    }

    public void setDescription(AIMFileWrapper description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_ASSIGNMENTS_STUDENT_SAMPLES",
            joinColumns = {
        @JoinColumn(name = "ASSIGNMENT_NAME")},
            inverseJoinColumns = {
        @JoinColumn(name = "STUDENT_SAMPLE_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public Set<StudentSample> getSamples() {
        return samples;
    }

    public void setSamples(Set<StudentSample> samples) {
        this.samples = samples;
    }

    @Override
    public String toString() {
        return "Assignment";
    }

    @Override
    public Serializable primaryKey() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
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
        final Assignment other = (Assignment) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
}

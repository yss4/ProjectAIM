package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.PeoBean;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "AIM_ENTITY_PEO")
public class PEO implements AIMEntity, Serializable {
    /***************************************************************************
     * Information maintained about each PEO includes:                         *
     **************************************************************************/
    /* ID: User-specified identifier for the PEO                              */
    private String id;

    /* Sequence Number: Number used to order PEOs                             */
    private Integer sequence;
    
    /* Short Name: Used to identify the PEO in column headings                */
    private String shortName;
    
    /* Description                                                            */
    private String description;
    
    /* Target Attainment Level in each semester                               */
    private Set<AttainmentLevel> targetAttainmentLevels;

    /***************************************************************************
     * Bi-Directional mappings from other AIMEntities:                         *
     **************************************************************************/
    private Set<DegreeProgram> degreePrograms;

    public PEO() {
        degreePrograms = new HashSet<DegreeProgram>();
        targetAttainmentLevels = new HashSet<AttainmentLevel>();
    }

    public PEO(PeoBean peoBean) {
        id = peoBean.getId();
        sequence = peoBean.getSequence();
        shortName = peoBean.getShortName();
        description = peoBean.getDescription();
        targetAttainmentLevels = peoBean.getTargetAttainmentLevels();
    }

    @Id
    @Column(name = "PEO_ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "PEO_SEQUENCE")
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Column(name = "PEO_SHORT_NAME")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Column(name = "PEO_DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_PEOS_ATTAINMENT_TARGETS",
            joinColumns = {
        @JoinColumn(name = "PEO_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "ATTAINMENT_TARGET_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<AttainmentLevel> getTargetAttainmentLevels() {
        return targetAttainmentLevels;
    }

    public void setTargetAttainmentLevels(Set<AttainmentLevel> targetAttainmentLevels) {
        this.targetAttainmentLevels = targetAttainmentLevels;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_DEGREE_PROGRAMS_PEOS",
            joinColumns = {
        @JoinColumn(name = "PEO_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "DEGREE_PROGRAM_ID")})
    @Fetch(value = FetchMode.SUBSELECT)
    public Set<DegreeProgram> getDegreePrograms() {
        return degreePrograms;
    }

    public void setDegreePrograms(Set<DegreeProgram> degreePrograms) {
        this.degreePrograms = degreePrograms;
    }

    @Override
    public String toString() {
        return "PEO";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.sequence != null ? this.sequence.hashCode() : 0);
        hash = 53 * hash + (this.shortName != null ? this.shortName.hashCode() : 0);
        hash = 53 * hash + (this.description != null ? this.description.hashCode() : 0);
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
        final PEO other = (PEO) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
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

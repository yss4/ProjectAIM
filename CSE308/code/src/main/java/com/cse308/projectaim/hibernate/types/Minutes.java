package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.MinuteBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "AIM_ENTITY_MINUTES")
public class Minutes implements Serializable, AIMEntity {
    /***************************************************************************
     * Information maintained about each Minutes includes:                     *
     **************************************************************************/
    /* ID: Used to identify the minutes                                       */
    private int id;
    
    /* Date: Date of the meeting                                              */
    private Date date;
    
    /* Group: Group that met (ex: "Advisory Board", "CIC")                    */
    private String group;
    
    /* Minutes: File containing the minutes of the meeting                    */
    private AIMFileWrapper minutes;

    /* Degree Programs: the relevant degree Programs                          */
    private Set<DegreeProgram> degreePrograms;
    
    public Minutes() {
    }

    public Minutes(MinuteBean minuteBean) {
        id = minuteBean.getId();
        date = minuteBean.getDate();
        group = minuteBean.getGroup();
        minutes = minuteBean.getMinutes();
        degreePrograms = minuteBean.getDegreePrograms();
    }
    
    @Id
    @Column(name = "MINUTES_ID")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "MINUTES_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "MINUTES_GROUP")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_MINUTES_FILES",
            joinColumns = {
        @JoinColumn(name = "MINUTES_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "FILE_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public AIMFileWrapper getMinutes() {
        return minutes;
    }

    public void setMinutes(AIMFileWrapper minutes) {
        this.minutes = minutes;
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_MINUTES_DEGREE_PROGRAMS",
            joinColumns = {
        @JoinColumn(name = "MINUTES_ID")},
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
        return "Minutes";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
        hash = 17 * hash + (this.group != null ? this.group.hashCode() : 0);
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
        final Minutes other = (Minutes) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.group == null) ? (other.group != null) : !this.group.equals(other.group)) {
            return false;
        }
        return true;
    }
}

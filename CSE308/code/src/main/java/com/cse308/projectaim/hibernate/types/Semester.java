package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.SemesterBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;

@Entity(name = "AIM_ENTITY_SEMESTER")
public class Semester implements AIMEntity, Serializable {

    private SemesterPK semester;
    private String displayName;
    private Date startDate;
    private Date endDate;

    public Semester() {
    }

    public Semester(SemesterPK primaryKey) {
        semester = primaryKey;
    }

    public Semester(SemesterBean semesterBean) {
        semester = semesterBean.getSemester();
        displayName = semesterBean.getDisplayName();
        startDate = semesterBean.getStartDate();
        endDate = semesterBean.getEndDate();
    }

    @EmbeddedId
    public SemesterPK getSemester() {
        return semester;
    }

    public void setSemester(SemesterPK semester) {
        this.semester = semester;
    }

    @Column(name = "SEMESTER_DISPLAY_NAME")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "SEMESTER_START_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = (startDate == null) ? null : new Date(startDate.getTime());
    }

    @Column(name = "SEMESTER_END_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = (endDate == null) ? null : new Date(endDate.getTime());
    }

    @Override
    public String toString() {
        return "Semester";
    }

    @Override
    public Serializable primaryKey() {
        return this.semester;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.semester != null ? this.semester.hashCode() : 0);
        hash = 67 * hash + (this.displayName != null ? this.displayName.hashCode() : 0);
        hash = 67 * hash + (this.startDate != null ? this.startDate.hashCode() : 0);
        hash = 67 * hash + (this.endDate != null ? this.endDate.hashCode() : 0);
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
        final Semester other = (Semester) obj;
        if (this.semester != other.semester && (this.semester == null || !this.semester.equals(other.semester))) {
            return false;
        }
        if ((this.displayName == null) ? (other.displayName != null) : !this.displayName.equals(other.displayName)) {
            return false;
        }
        if (this.startDate != other.startDate && (this.startDate == null || !this.startDate.equals(other.startDate))) {
            return false;
        }
        if (this.endDate != other.endDate && (this.endDate == null || !this.endDate.equals(other.endDate))) {
            return false;
        }
        return true;
    }
}
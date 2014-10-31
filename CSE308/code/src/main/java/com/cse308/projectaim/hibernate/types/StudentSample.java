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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "AIM_ENTITY_STUDENT_SAMPLE")
public class StudentSample implements Serializable, AIMEntity {
    /***************************************************************************
     * Information maintained about each CourseOffering includes:              *
     * ************************************************************************/
    /* ID: Used to identify the sample                                        */
    private int id;

    /* Quality: Quality of this student sample (1: Good, 2: Average, 3: Poor) */
    private int quality;

    /* Content: File containing the student's submission for this assignment  */
    private AIMFileWrapper content;

    public StudentSample() {
    }

    @Id
    @GeneratedValue
    @Column(name = "STUDENT_SAMPLE_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "STUDENT_SAMPLE_QUALITY")
    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_STUDENT_SAMPLES_FILES",
            joinColumns = {
        @JoinColumn(name = "STUDENT_SAMPLE_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "FILE_ID")})
    @Cascade(CascadeType.SAVE_UPDATE)
    public AIMFileWrapper getContent() {
        return content;
    }

    public void setContent(AIMFileWrapper content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + this.quality;
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
        final StudentSample other = (StudentSample) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quality != other.quality) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentSample";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }
}

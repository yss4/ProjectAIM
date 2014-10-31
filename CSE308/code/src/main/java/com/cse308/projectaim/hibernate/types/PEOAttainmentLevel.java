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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "AIM_ENTITY_PEO_ATTAINMENT_LEVEL")
public class PEOAttainmentLevel implements AIMEntity, Serializable {
    private int id;
    private PEO peo;
    private double level;

    public PEOAttainmentLevel() {
    }
    
    @Id
    @Column(name = "PEO_ATTAINMENT_LEVEL_ID")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_PEO_ATTAINMENT_LEVEL_PEO",
            joinColumns = {
        @JoinColumn(name = "PEO_ATTAINMENT_LEVEL_ID")},
            inverseJoinColumns = {
        @JoinColumn(name = "PEO_ID")})
    public PEO getPeo() {
        return peo;
    }

    public void setPeo(PEO peo) {
        this.peo = peo;
    }

    @Column(name = "PEO_ATTAINMENT_LEVEL_LEVEL")
    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "PEOAttainmentLevel";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }
}

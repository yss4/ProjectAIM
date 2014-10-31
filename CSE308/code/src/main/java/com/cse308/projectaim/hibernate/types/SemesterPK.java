package com.cse308.projectaim.hibernate.types;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SemesterPK implements Serializable {

    private Integer year;
    private Integer term;

    public SemesterPK() {
    }

    public SemesterPK(Integer year, Integer term) {
        this.year = year;
        this.term = term;
    }

    @Column(name="SEMESTER_YEAR")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Column(name="SEMESTER_TERM")
    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 11 * hash + (this.year != null ? this.year.hashCode() : 0);
		hash = 11 * hash + (this.term != null ? this.term.hashCode() : 0);
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
		final SemesterPK other = (SemesterPK) obj;
		if (this.year != other.year && (this.year == null || !this.year.equals(other.year))) {
			return false;
		}
		if (this.term != other.term && (this.term == null || !this.term.equals(other.term))) {
			return false;
		}
		return true;
	}    	
}

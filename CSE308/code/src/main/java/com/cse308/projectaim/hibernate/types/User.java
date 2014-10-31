package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "AIM_ENTITY_USER")
public class User implements AIMEntity, Serializable {
    /***************************************************************************
     * Information maintained about each User includes:                        *
     **************************************************************************/
    /* Username: The login and display name of the user                       */
    private String username;
    
    /* Password:  The user's encrypted password                               */
    private String password;
    
    /* e-Mail Address: The user's e-Mail address                              */
    private String emailAddress;

    /* CIC Member Status: Boolean value indicating CIC member status          */
    private Boolean cicMemberStatus;
    
    /* Evaluator: Boolean value indicating ABET evaluator status              */
    private Boolean evaluatorStatus;
    
    /* Evaluation Period: Range of time that this user is evaluating          */
    private Date evaluationStartDate;
    private Date evaluationEndDate;
    
    /* Degree Programs: Degree Programs with which the user is associated     */
    private Set<DegreeProgram> degreePrograms;

    public User() {
        degreePrograms = new HashSet<DegreeProgram>();
    }

    public User(String primaryKey) {
        username = primaryKey;
    }

    @Id
    @Column(name = "USER_USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "USER_PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "USER_EMAIL")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Column(name = "USER_CIC_STATUS")
    public Boolean getCicMemberStatus() {
        return cicMemberStatus;
    }

    public void setCicMemberStatus(Boolean cicMemberStatus) {
        this.cicMemberStatus = cicMemberStatus;
    }

    @Column(name = "USER_EVALUATOR_STATUS")
    public Boolean getEvaluatorStatus() {
        return evaluatorStatus;
    }

    public void setEvaluatorStatus(Boolean evaluatorStatus) {
        this.evaluatorStatus = evaluatorStatus;
    }

    @Column(name = "USER_EVALUATOR_START_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getEvaluationStartDate() {
        return evaluationStartDate;
    }

    public void setEvaluationStartDate(Date evaluationStartDate) {
        this.evaluationStartDate = (evaluationStartDate == null) ? null : new Date(evaluationStartDate.getTime());
    }

    @Column(name = "USER_EVALUATOR_END_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getEvaluationEndDate() {
        return evaluationEndDate;
    }

    public void setEvaluationEndDate(Date evaluationEndDate) {
        this.evaluationEndDate = (evaluationEndDate == null) ? null : new Date(evaluationEndDate.getTime());
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_DEGREE_PROGRAMS_USERS",
            joinColumns = {
        @JoinColumn(name = "USER_USERNAME")},
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
        return "User";
    }

    @Override
    public Serializable primaryKey() {
        return this.username;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.username);
        hash = 37 * hash + Objects.hashCode(this.password);
        hash = 37 * hash + Objects.hashCode(this.emailAddress);
        hash = 37 * hash + Objects.hashCode(this.cicMemberStatus);
        hash = 37 * hash + Objects.hashCode(this.evaluatorStatus);
        hash = 37 * hash + Objects.hashCode(this.evaluationStartDate);
        hash = 37 * hash + Objects.hashCode(this.evaluationEndDate);
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        if (!Objects.equals(this.cicMemberStatus, other.cicMemberStatus)) {
            return false;
        }
        if (!Objects.equals(this.evaluatorStatus, other.evaluatorStatus)) {
            return false;
        }
        if (!Objects.equals(this.evaluationStartDate, other.evaluationStartDate)) {
            return false;
        }
        if (!Objects.equals(this.evaluationEndDate, other.evaluationEndDate)) {
            return false;
        }
        return true;
    }

    public boolean checkUsername() {
        if (username == null || username.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkPassword() {
        if (password == null || password.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkEmailStatus() {
        if (emailAddress == null || emailAddress.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}

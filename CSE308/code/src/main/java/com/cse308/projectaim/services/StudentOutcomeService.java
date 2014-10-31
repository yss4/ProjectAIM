package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.StudentOutcomeBean;
import com.cse308.projectaim.hibernate.types.StudentOutcome;

public class StudentOutcomeService extends AIMService {

    public boolean create(StudentOutcomeBean studentOutcomeBean) {
        try {
            aimStore.create(new StudentOutcome(studentOutcomeBean));
        } catch (ProjectAimException ex) {
            logger.error("Failed to create StudentOutcome: " + ex.getMessage());
            return false;
        }
        return true;
    }
}

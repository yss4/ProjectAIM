package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.DegreeProgramBean;
import com.cse308.projectaim.hibernate.types.DegreeProgram;

public class DegreeProgramService extends AIMService {

    public boolean create(DegreeProgramBean degreeProgamBean) {
        try {
            aimStore.create(new DegreeProgram(degreeProgamBean));
        } catch (ProjectAimException ex) {
            logger.error("Failed to create DegreeProgram: " + ex.getMessage());
            return false;
        }
        return true;
    }
}

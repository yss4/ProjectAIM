package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.AssignmentBean;
import com.cse308.projectaim.hibernate.types.Assignment;

public class AssignmentService extends AIMService {
    
    public boolean create(AssignmentBean assignmentBean) {
        try {
            Assignment assignment = new Assignment(assignmentBean);
            aimStore.create(assignment);
        } catch (ProjectAimException ex) {
            logger.error("Failed to create Assignment: " + ex.getMessage());
            return false;
        }
        return true;
    }    
}

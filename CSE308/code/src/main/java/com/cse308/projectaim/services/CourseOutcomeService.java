package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.CourseOutcomeBean;
import com.cse308.projectaim.hibernate.types.CourseOutcome;

public class CourseOutcomeService extends AIMService {

    public boolean create(CourseOutcomeBean courseOutcomeBean) {
        try {
            CourseOutcome co = new CourseOutcome(courseOutcomeBean);
            aimStore.create(co);
            courseOutcomeBean.setId(co.getId());
        } catch (ProjectAimException ex) {
            logger.error("Failed to create CourseOutcome: " + ex.getMessage());
            return false;
        }
        return true;
    }
}

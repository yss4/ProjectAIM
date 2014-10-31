package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.CourseOutcomeDirectAssessmentBean;
import com.cse308.projectaim.hibernate.types.CourseOutcomeDirectAssessment;

public class CourseOutcomeDirectAssessmentService extends AIMService {

    public boolean create(CourseOutcomeDirectAssessmentBean courseOutcomeDirectAssessmentBean) {
        try {
            aimStore.create(new CourseOutcomeDirectAssessment(courseOutcomeDirectAssessmentBean));
        } catch (ProjectAimException ex) {
            logger.error("Failed to create CourseOutcomeDirectAssessment: " + ex.getMessage());
            return false;
        }
        return true;
    }
}

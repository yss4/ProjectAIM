package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.CourseOfferingBean;
import com.cse308.projectaim.hibernate.types.CourseOffering;

public class CourseOfferingService extends AIMService {

    public boolean create(CourseOfferingBean courseOfferingBean) {
        try {
            CourseOffering co = new CourseOffering(courseOfferingBean);
            aimStore.create(co);
            courseOfferingBean.setSectionNumber(co.getSectionNumber());
        } catch (ProjectAimException ex) {
            logger.error("Failed to create CourseOffering: " + ex.getMessage());
            return false;
        }
        return true;
    }
}

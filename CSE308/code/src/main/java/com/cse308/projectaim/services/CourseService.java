package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.CourseBean;
import com.cse308.projectaim.hibernate.types.Course;

public class CourseService extends AIMService {

    public boolean create(CourseBean courseBean) {
        try {
            aimStore.create(new Course(courseBean));
        } catch (ProjectAimException ex) {
            logger.error("Failed to create Course: " + ex.getMessage());
            return false;
        }
        return true;
    }
}

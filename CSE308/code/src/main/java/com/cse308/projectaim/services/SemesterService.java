package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.SemesterBean;
import com.cse308.projectaim.hibernate.types.Semester;

public class SemesterService extends AIMService {

    public boolean create(SemesterBean semesterBean) {
        try {
            aimStore.create(new Semester(semesterBean));
        } catch (ProjectAimException ex) {
            logger.error("Failed to create Semester: " + ex.getMessage());
            return false;
        }
        return true;
    }
}
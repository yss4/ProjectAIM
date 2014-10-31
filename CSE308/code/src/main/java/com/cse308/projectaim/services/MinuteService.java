package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.MinuteBean;
import com.cse308.projectaim.hibernate.types.Minutes;

public class MinuteService extends AIMService {
    
    public boolean create(MinuteBean minuteBean) {
        try {
            aimStore.create(new Minutes(minuteBean));
        } catch (ProjectAimException ex) {
            logger.error("Failed to create Minute: " + ex.getMessage());
            return false;
        }
        return true;
    }
}

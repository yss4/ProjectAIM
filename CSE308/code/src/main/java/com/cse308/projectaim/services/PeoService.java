package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.beans.PeoBean;
import com.cse308.projectaim.hibernate.types.PEO;

public class PeoService extends AIMService {

    public boolean create(PeoBean peoBean) {
        try {
            aimStore.create(new PEO(peoBean));
        } catch (ProjectAimException ex) {
            logger.error("Failed to create PEO: " + ex.getMessage());
            return false;
        }
        return true;
    }
}

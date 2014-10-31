package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.types.PEOAttainmentLevel;

public class PeoAttainmentLevelService extends AIMService {
    public boolean create(PEOAttainmentLevel peoAttainmentLevel) {
        try {
            aimStore.create(peoAttainmentLevel);
            peoAttainmentLevel.setId(peoAttainmentLevel.getId());
        } catch (ProjectAimException ex) {
            logger.error("Failed to create PEOAttainmentLevel: " + ex.getMessage());
            return false;
        }
        return true;
    }
	
}

package com.cse308.projectaim.services;

import com.cse308.projectaim.ProjectAimException;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.AIMStore;
import java.util.List;
import org.apache.log4j.Logger;

public class AIMService {
	protected Logger logger = Logger.getLogger(getClass());
	protected AIMStore aimStore = AIMStore.getInstance();

	public List<AIMEntity> getAll(AIMEntity query) {
		logger.info("Getting a list of AIMEntities [" + query.toString() + "]");
		List<AIMEntity> list = null;

		try {
			list = aimStore.search(query);
		} catch (ProjectAimException ex) {
			logger.error("Failed to get a list of AIMEntities: " + ex.getMessage());
		}

		return list;
	}

        public AIMEntity retrieve(AIMEntity entity) {
		logger.info("Retrieving AIMEntity [" + entity.toString() + "]");
                AIMEntity entityRetrieving;
		try {
			entityRetrieving= aimStore.retrieve(entity);
		} catch (ProjectAimException ex) {
			logger.error("Failed to retrieve AIMEntity: " + ex.getMessage());
			return null;
		}
		
		return entityRetrieving;
	}
        
	public boolean update(AIMEntity entity) {
		logger.info("Updating AIMEntity [" + entity.toString() + "]");

		try {
			aimStore.update(entity);
		} catch (ProjectAimException ex) {
			logger.error("Failed to update AIMEntity: " + ex.getMessage());
			return false;
		}
		
		return true;
	}

	public boolean delete(AIMEntity entity) {
		logger.info("Deleting AIMEntity [" + entity.toString() + "]");

		try {
			aimStore.delete(entity);
		} catch (ProjectAimException ex) {
			logger.error("Failed to delete AIMEntity: " + ex.getMessage());
			return false;
		}

		return true;
	}
}

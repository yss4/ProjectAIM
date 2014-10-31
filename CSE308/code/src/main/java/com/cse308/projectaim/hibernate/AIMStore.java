package com.cse308.projectaim.hibernate;

import com.cse308.projectaim.ProjectAimException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

public class AIMStore {

    private static AIMStore singleton = new AIMStore();
    protected Logger logger = Logger.getLogger(getClass());
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected Session session = null;
    
    private AIMStore(){        
    }
    
    public static AIMStore getInstance(){
        return singleton;
    }

    public void create(AIMEntity entity) throws ProjectAimException {
        if (entity == null) 
            throw new ProjectAimException("Attempt to create NULL entity recieved");

        try {
            retrieve(entity);
            logger.error("Attempt to create duplicate primary key received.");
            throw new ProjectAimException("Attempt to create duplicate primary key received");
        } catch (Exception e) {
            logger.debug("Creating AIMEntity [" + entity.toString() + "]");
        }

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            session.flush();
            closeSession();
        } catch (Exception ex) {
            logger.debug("Failed to create AIMEntity: " + ex.getMessage());
            closeSession();
            throw new ProjectAimException("Could not persist AIMEntity: " + ex.getMessage(), ex);
        }
    }

    public AIMEntity retrieve(AIMEntity entity) throws ProjectAimException {
        if (entity == null) {
            throw new ProjectAimException("Attempt to retrieve NULL entity recieved");
        }
        logger.debug("Retrieving AIMEntity [" + entity.toString() + "]");
        try {
            AIMEntity result;
            session = sessionFactory.openSession();
            session.beginTransaction();
            result = (AIMEntity) session.get(entity.getClass(), entity.primaryKey());
            if (result == null) {
                throw new ProjectAimException("AIMEntity not found");
            }
            session.flush();
            closeSession();
            return result;
        } catch (Exception ex) {
            logger.debug("Failed to retrieve AIMEntity: " + ex.getMessage());
            closeSession();
            throw new ProjectAimException("Could not retrieve AIMEntity: " + ex.getMessage(), ex);
        }
    }

    public void update(AIMEntity entity) throws ProjectAimException {
        if (entity == null) {
            throw new ProjectAimException("Attempt to update NULL entity recieved");
        }
        logger.debug("Updating AIMEntity [" + entity.toString() + "]");
        try {
            retrieve(entity);
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.flush();
            closeSession();
        } catch (Exception ex) {
            logger.debug("Failed to update AIMEntity: " + ex.getMessage());
            closeSession();
            throw new ProjectAimException("Could not update AIMEntity: " + ex.getMessage(), ex);
        }
    }

    public void delete(AIMEntity entity) throws ProjectAimException {
        if (entity == null) {
            throw new ProjectAimException("Attempt to delete NULL entity recieved.");
        } else if (entity.primaryKey() == null) {
            throw new ProjectAimException("Attempt to delete entity with no primary key recieved.");
        }
        logger.debug("Deleting AIMEntity [" + entity.toString() + "]");
        try {
            retrieve(entity);
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            session.flush();
            closeSession();
        } catch (Exception ex) {
            logger.debug("Failed to delete AIMEntity: " + ex.getMessage());
            closeSession();
            throw new ProjectAimException("Could not delete AIMEntity: " + ex.getMessage(), ex);
        }
    }

    public List<AIMEntity> search(AIMEntity entity) throws ProjectAimException {
        if (entity == null) {
            throw new ProjectAimException("Unable to search: Attempt to search on NULL entity.");
        }
        logger.debug("Searching AIMEntity [" + entity.toString() + "]");
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(entity.getClass());
            criteria.add(Example.create(entity).ignoreCase().excludeZeroes());
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List results = criteria.list();
            session.flush();
            closeSession();
            logger.debug("Search found " + results.size() + " entities.");
            return results;
        } catch (Exception e) {
            closeSession();
            throw new ProjectAimException("Unable to search: " + e.getMessage(), e);
        }
    }

    private void closeSession() {
        if (session != null) {
            if (session.isOpen()) {
                session.close();
            }
        }
    }
}

package com.kharevich.repository;

import com.kharevich.domain.BaseEntity;
import com.kharevich.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class RepositoryImpl implements Repository {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryImpl.class);

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    public RepositoryImpl(EntityManagerFactory emf) {
        checkNotNull(emf, "EntityManagerFactory can not be null");
        this.entityManagerFactory = emf;
    }

    @Override
    public <T extends BaseEntity> T save(T obj) {
        logger.debug("saving entity " + obj);
        execute(new CallBack<Void>() {
            @Override
            public <S extends Serializable> Void doAction(S obj) {
                entityManager.persist(obj);
                return null;
            }
        }, obj);
        logger.debug("entity was saved " + obj);
        return obj;
    }

    @Override
    public <T extends BaseEntity> void delete(T obj) {
        logger.debug("deleting entity " + obj);
        execute(new CallBack<Void>() {
            @Override
            public <S extends Serializable> Void doAction(S obj) {
                entityManager.remove(obj);
                return null;
            }
        }, obj);
        logger.debug("entity was deleted " + obj);
    }

    @Override
    public <T extends BaseEntity<PK>, PK extends Serializable> T get(final Class<T> clazz, PK pk) {
        logger.debug("finding entity with PK " + pk);
        T obj = execute(new CallBack<T>() {
            @Override
            public <O extends Serializable> T doAction(O pk) {
                return entityManager.find(clazz, pk);
            }
        }, pk);
        logger.debug("entity with " + pk + " was found " + obj);
        return obj;
    }

    @Override
    public <T extends BaseEntity> void update(T obj) {
        logger.debug("updating entity " + obj);
        execute(new CallBack<Void>() {
            @Override
            public <S extends Serializable> Void doAction(S obj) {
                entityManager.merge(obj);
                return null;
            }
        }, obj);
        logger.debug("entity was updated " + obj);
    }

    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    private <T, S extends Serializable> T execute(CallBack<T> method, S obj) {
        checkNotNull(obj, "object can not be null");
        entityManager = entityManagerFactory.createEntityManager();
        T result = method.doAction(obj);
        entityManager.flush();
        return result;
    }

    private EntityManager getEntityManager(){
        if(entityManager!=null && entityManager.isOpen())
            return entityManager;
        return entityManager = entityManagerFactory.createEntityManager();
    }

    interface CallBack<T> {
        <S extends Serializable> T doAction(S obj);
    }

    public Integer countPersons() {
        Query query = entityManager.createQuery("SELECT COUNT(p.id) FROM User p");
        return ((Long) query.getSingleResult()).intValue();
    }

    public <T extends BaseEntity> List<T> findPersons(final Class<T> clazz,int startPosition, int maxResults, String sortFields, String sortDirections) {
        TypedQuery<T> query = entityManager.createQuery("SELECT p FROM User p ", clazz);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

}



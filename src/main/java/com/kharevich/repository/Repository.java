package com.kharevich.repository;


import com.kharevich.domain.BaseEntity;

import java.io.Closeable;
import java.io.Serializable;
import java.util.List;


public interface Repository extends Closeable {

    <T extends BaseEntity> T save(T obj);

    <T extends BaseEntity> void delete(T obj);

    <T extends BaseEntity<PK>, PK extends Serializable> T get(Class<T> clazz, PK id);

    <T extends BaseEntity> void update(T obj);

    <T extends BaseEntity> List<T> findPersons(final Class<T> clazz,int startPosition, int maxResults, String sortFields, String sortDirections);

    Integer countPersons();


}

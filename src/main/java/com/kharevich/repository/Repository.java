package com.kharevich.repository;


import com.kharevich.domain.BaseEntity;

import java.io.Closeable;
import java.io.Serializable;


public interface Repository extends Closeable {

    public <T extends BaseEntity> T save(T obj);

    public <T extends BaseEntity> void delete(T obj);

    public <T extends BaseEntity<PK>, PK extends Serializable> T get(Class<T> clazz, PK id);

    public <T extends BaseEntity> void update(T obj);

}

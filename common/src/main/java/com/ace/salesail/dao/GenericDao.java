package com.ace.salesail.dao;


import com.ace.salesail.domain.Persistent;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface GenericDao {

    public void flush();

    public void clear();

    public <T extends Persistent> T get(Class<T> clazz, Serializable id);

    public <T extends Persistent> List<T> getAll(Class<T> clazz);

    public <T extends Serializable> List<T> getByCriteria(final DetachedCriteria dc);

    public <T extends Serializable> List<T> getByQuery(String query);

    public <T extends Serializable> List<T> getByQuery(final String query, Object... values);

    public <T extends Serializable> T getOneByCriteria(final DetachedCriteria dc);

    public <T extends Persistent> void saveOrUpdate(T entity);

    public <T extends Persistent> void saveOrUpdateAll(List<T> entities);

    public <T extends Persistent> void delete(T entity);

    public <T extends Persistent> void deleteAll(Collection<T> entities);

    public <T extends Persistent> void deleteAllByEqualProperty(Class<T> clazz, String property, Object value);

    public <T extends Persistent> void reassociate(T entity);

    public <T extends Persistent> void evict(T entity);

    public <T extends Persistent> void refresh(T entity);
}

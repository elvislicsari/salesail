package com.ace.salesail.manager;

import com.ace.salesail.dao.GenericDao;
import com.ace.salesail.domain.Persistent;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class GenericManager {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected GenericDao dao;

    protected GenericDao getDao() {
        return this.dao;
    }

    public void setDao(GenericDao dao) {
        this.dao = dao;
    }

    public <T extends Persistent> T get(Class<T> clazz, Serializable id) {
        return this.getDao().get(clazz, id);
    }

    public <T extends Persistent> List<T> getAll(Class<T> clazz) {
        return this.getDao().getAll(clazz);
    }

    public <T extends Persistent> List<T> getAll(Class<T> clazz, Order... orders) {
        final DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        for (Order o : orders) {
            dc.addOrder(o);
        }
        return this.dao.getByCriteria(dc);
    }

    public <T extends Persistent> List<T> getByCriteria(DetachedCriteria dc) {
        return this.getDao().getByCriteria(dc);
    }

    public <T extends Persistent> List<T> getByEqualProperty(Class<T> clazz, String property, Object value) {
        return this.dao.getByCriteria(DetachedCriteria.forClass(clazz)
                                                      .add(Restrictions.eq(property, value)));
    }

    public <T extends Persistent> List<T> getByEqualProperty(Class<T> clazz, String property, Object value, Order... orders) {
        final DetachedCriteria dc = DetachedCriteria.forClass(clazz).add(Restrictions.eq(property, value));
        for (Order o : orders) {
            dc.addOrder(o);
        }
        return this.dao.getByCriteria(dc);
    }

    public <T extends Serializable> List<T> getByQuery(String query) {
        return this.dao.getByQuery(query);
    }

    public <T extends Serializable> List<T> getByQuery(String query, Object... values) {
        return this.dao.getByQuery(query, values);
    }


    public <T extends Persistent> T getOneByCriteria(DetachedCriteria dc) {
        return (T) this.getDao().getOneByCriteria(dc);
    }

    public <T extends Persistent> T getOneByEqualProperty(Class<T> clazz, String property, Object value) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.eq(property, value));
        Object e = this.getDao().getOneByCriteria(dc);
        if (e != null)
            return (T) e;
        return null;
    }

    public <T extends Persistent> T getOneByEqualProperty(Class<T> clazz, String property1, Object value1, String property2, Object value2) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.eq(property1, value1)).add(Restrictions.eq(property2, value2));
        Object e = this.getDao().getOneByCriteria(dc);
        if (e != null)
            return (T) e;
        return null;
    }

    public <T extends Persistent> T getOneByIlikeProperty(Class<T> clazz, String property, String value) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.ilike(property, value));
        return (T) this.getDao().getOneByCriteria(dc);
    }

    public void saveOrUpdate(Persistent entity) {
        this.getDao().saveOrUpdate(entity);
    }

    public void refresh(Persistent entity) {
        this.getDao().refresh(entity);
    }

    public void delete(Persistent entity) {
        this.getDao().delete(entity);
    }

    public void deleteAll(Collection<? extends Persistent> entities) {
        this.getDao().deleteAll(entities);
    }

    public void deleteAllByEqualProperty(final Class<? extends Persistent> clazz, final String property, final Object value) {
        this.getDao().deleteAllByEqualProperty(clazz, property, value);
    }

    public void reassociate(Persistent entity) {
        this.getDao().reassociate(entity);
    }

    public void evict(Persistent entity) {
        this.getDao().evict(entity);
    }

    public void flush() {
        this.getDao().flush();
    }
}

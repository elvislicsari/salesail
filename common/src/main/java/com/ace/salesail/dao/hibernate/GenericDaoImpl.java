package com.ace.salesail.dao.hibernate;


import com.ace.salesail.dao.GenericDao;
import com.ace.salesail.domain.Persistent;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;


@SuppressWarnings("unchecked")
public class GenericDaoImpl extends HibernateDaoSupport implements GenericDao {

    @Override
    public <T extends Persistent> T get(final Class<T> clazz, final Serializable id) {
        return clazz.cast(this.getHibernateTemplate().get(clazz, id));
    }


    @Override
    public <T extends Persistent> List<T> getAll(final Class<T> clazz) {
        final DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        return this.getHibernateTemplate().findByCriteria(dc);
    }

    @Override
    public <T extends Serializable> List<T> getByCriteria(final DetachedCriteria dc) {
        return this.getHibernateTemplate().findByCriteria(dc);
    }


    @Override
    public <T extends Serializable> List<T> getByQuery(final String query) {
        return this.getHibernateTemplate().find(query);
    }


    @Override
    public <T extends Serializable> List<T> getByQuery(final String query, Object... values) {
        return this.getHibernateTemplate().find(query, values);
    }


    @Override
    public <T extends Serializable> T getOneByCriteria(final DetachedCriteria dc) {
        final List<T> result = this.getHibernateTemplate().findByCriteria(dc, 0, 1);
        if (result != null && result.size() > 0)
            return result.get(0);
        else
            return null;
    }

    @Override
    public <T extends Persistent> void saveOrUpdate(final T entity) {
        if (entity.getId() == null)
            entity.setDateCreated(GregorianCalendar.getInstance().getTime());
        else
            entity.setDateUpdated(GregorianCalendar.getInstance().getTime());
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public <T extends Persistent> void saveOrUpdateAll(List<T> entities) {
        this.getHibernateTemplate().saveOrUpdateAll(entities);
    }


    @Override
    public <T extends Persistent> void delete(final T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public <T extends Persistent> void deleteAll(final Collection<T> entities) {
        this.getHibernateTemplate().deleteAll(entities);
    }

    @Override
    public <T extends Persistent> void deleteAllByEqualProperty(final Class<T> clazz, final String property, final Object value) {
        this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery("DELETE from " + clazz.getSimpleName() +
                                                        " e where e." + property + " = ?").addEntity(clazz);
                query.setParameter(0, value);
                logger.debug("DELETE query: " + query.getQueryString());
                return null;
            }
        });
    }


    @Override
    public <T extends Persistent> void reassociate(final T entity) {
        this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                session.lock(entity, LockMode.NONE);
                return null;
            }
        });
    }

    @Override
    public <T extends Persistent> void evict(final T entity) {
        this.getHibernateTemplate().evict(entity);
    }


    @Override
    public <T extends Persistent> void refresh(final T entity) {
        this.getHibernateTemplate().refresh(entity);
    }


    @Override
    public void flush() {
        this.getHibernateTemplate().flush();
    }


    @Override
    public void clear() {
        this.getHibernateTemplate().clear();
    }


}

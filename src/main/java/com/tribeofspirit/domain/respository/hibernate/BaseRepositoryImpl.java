package com.tribeofspirit.domain.respository.hibernate;

import com.tribeofspirit.domain.respository.BaseRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;

/**
 * Author : gonwang
 * Create time : 31.10.2015.
 */
public class BaseRepositoryImpl<T> extends HibernateDaoSupport implements BaseRepository<T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseRepositoryImpl() {

        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(clazz);
    }

    @Override
    public void saveOrUpdate(T bean) {
        getHibernateTemplate().saveOrUpdate(bean);
    }

    @Override
    public T get(Long id) {
        return getHibernateTemplate().get(clazz, id);
    }

    @Override
    public void delete(Long toDeleteId) {
        getHibernateTemplate().delete(get(toDeleteId));
    }

}

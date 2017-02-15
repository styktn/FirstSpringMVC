package com.springmvcproject.core.dao;

import org.hibernate.SessionFactory;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


/**
 * Created by yektan on 14.12.2016.
 */
@Repository
public class HibernateDAOImpl<T,Id extends Serializable> implements HibernateDAO<T,Id> {

    public HibernateDAOImpl(){
    }

    protected Class<T> persistentClass;

    public HibernateDAOImpl(Class<T> persistentClass) {
        //logger.debug("Object created. persistenClass parameter: " + persistentClass);
        this.persistentClass = persistentClass;
    }
    private static final Logger logger = LoggerFactory.getLogger(HibernateDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void add(T s) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(s);
        logger.info("Item saved successfully, Item Details="+s);
    }

    @Override
    public void update(T s) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(s);
        logger.info("Item updated successfully, Item Details="+s);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<T> list = session.createQuery("from "+persistentClass.getName()).list();
        for(T s : list){
            logger.info("List::"+s);
        }
        return list;
    }

    @Override
    public T getById(Id id) {
        Session session = this.sessionFactory.getCurrentSession();
        T s = (T) session.load(persistentClass, id);
        logger.info("Item loaded successfully, Item details="+s);
        return s;
    }

    @Override
    public void remove(Id id) {
        Session session = this.sessionFactory.getCurrentSession();
        T s = (T) session.load(persistentClass, id);
        if(null != s){
            session.delete(s);
        }
        logger.info("Item deleted successfully, Item details="+s);
    }

}

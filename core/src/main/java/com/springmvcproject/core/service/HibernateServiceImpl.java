package com.springmvcproject.core.service;

import com.springmvcproject.core.dao.HibernateDAO;
import com.springmvcproject.core.dao.HibernateDAOImpl;
import com.springmvcproject.core.model.Student;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yektan on 14.12.2016.
 */

@Service
public class HibernateServiceImpl<T,Id extends Serializable> implements HibernateService<T,Id> {

    private Class<T> persistentClass = null;

    // see: HibernateServiceImpl(Class<T> persistentClass, HibernateDao<T, Id> hibernateDao)
    HibernateDAO<T, Id> hibernateDAO;
    private HibernateDAOImpl studentDAO;

    public HibernateServiceImpl() {

    }

    public HibernateServiceImpl(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }
    public void setHibernateDAO(HibernateDAO<T,Id> hibernateDAO) {
        this.hibernateDAO = hibernateDAO;
    }
    @Override
    @Transactional
    public void add(T s) {
        this.hibernateDAO.add(s);
    }

    @Override
    @Transactional
    public void update(T s) {
        this.hibernateDAO.update(s);
    }

    @Override
    @Transactional
    public List<T> list() {
        return this.hibernateDAO.list();
    }

    @Override
    @Transactional
    public T getById(Id id) {
        return (T) this.hibernateDAO.getById(id);
    }

    @Override
    @Transactional
    public void remove(Id id) {
        this.hibernateDAO.remove(id);
    }


}

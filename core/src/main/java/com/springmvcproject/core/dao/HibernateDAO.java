package com.springmvcproject.core.dao;

import com.springmvcproject.core.model.Student;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yektan on 14.12.2016.
 */
public interface HibernateDAO <T,Id extends Serializable> extends Serializable {

    public void add(T s);
    public void update(T s);
    public List<T> list();
    public T getById(Id id);
    public void remove(Id id);

}

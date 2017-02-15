package com.springmvcproject.core.dao;

import com.springmvcproject.core.model.Student;
import org.springframework.stereotype.Repository;

/**
 * Created by yektan on 29.12.2016.
 */
@Repository
public class StudentDAOImpl extends HibernateDAOImpl<Student,String>{
    public StudentDAOImpl(){super(Student.class);}
    public StudentDAOImpl(Class<Student> persistentClass) {
        super(persistentClass);
    }
}


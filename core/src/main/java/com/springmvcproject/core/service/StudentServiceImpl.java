package com.springmvcproject.core.service;

import com.springmvcproject.core.dao.HibernateDAOImpl;
import com.springmvcproject.core.dao.StudentDAOImpl;
import com.springmvcproject.core.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by yektan on 29.12.2016.
 */
@org.springframework.stereotype.Service("studentService")
public class StudentServiceImpl extends HibernateServiceImpl<Student, Integer>  {
    @Autowired
    private StudentDAOImpl studentDAO;

    public StudentServiceImpl() {
        super(Student.class);
    }

    @PostConstruct
    public void init() {
        setHibernateDAO((HibernateDAOImpl<Student,Integer>)((HibernateDAOImpl<?,?>)studentDAO));
    }

    public void setStudentDAO(StudentDAOImpl studentDAO) {
        this.studentDAO = studentDAO;
    }

    public StudentDAOImpl getStudentDAO() {
        return studentDAO;
    }
}

package com.springmvcproject.core.dao;

import com.springmvcproject.core.model.Content;
import org.springframework.stereotype.Repository;

/**
 * Created by yektan on 29.12.2016.
 */
@Repository
public class ContentDAOImpl extends HibernateDAOImpl<Content,String>{
    public ContentDAOImpl(){super(Content.class);}
    public ContentDAOImpl(Class<Content> persistentClass) {
        super(persistentClass);
    }
}

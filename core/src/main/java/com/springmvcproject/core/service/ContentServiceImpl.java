package com.springmvcproject.core.service;

import com.springmvcproject.core.dao.AnnouncementDAOImpl;
import com.springmvcproject.core.dao.ContentDAOImpl;
import com.springmvcproject.core.dao.HibernateDAO;
import com.springmvcproject.core.dao.HibernateDAOImpl;
import com.springmvcproject.core.model.Content;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by yektan on 29.12.2016.
 */
@org.springframework.stereotype.Service("contentService")
public class ContentServiceImpl extends HibernateServiceImpl<Content, Integer>  {
    @Autowired
    ContentDAOImpl contentDAO;
    private ContentDAOImpl contentService;

    public ContentServiceImpl() {
        super(Content.class);
    }

    @PostConstruct
    public void init() {
        setHibernateDAO((HibernateDAOImpl<Content,Integer>)((HibernateDAOImpl<?,?>)contentDAO));
    }

    public void setContentService(ContentDAOImpl contentService) {
        this.contentService = contentService;
    }

    public ContentDAOImpl getContentService() {
        return contentService;
    }

}

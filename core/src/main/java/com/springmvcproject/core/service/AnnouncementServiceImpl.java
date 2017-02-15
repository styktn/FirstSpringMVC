package com.springmvcproject.core.service;

import com.springmvcproject.core.dao.AnnouncementDAOImpl;
import com.springmvcproject.core.dao.HibernateDAOImpl;
import com.springmvcproject.core.model.Announcement;
import com.springmvcproject.core.model.Content;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by yektan on 12.02.2017.
 */
@org.springframework.stereotype.Service("announcementService")
public class AnnouncementServiceImpl extends HibernateServiceImpl<Announcement, Integer>{
    @Autowired
    AnnouncementDAOImpl announcementDAO;
    private AnnouncementDAOImpl announcementService;

    public AnnouncementServiceImpl() {
        super(Announcement.class);
    }

    @PostConstruct
    public void init() {
        setHibernateDAO((HibernateDAOImpl<Announcement,Integer>)((HibernateDAOImpl<?,?>)announcementDAO));
    }

    public void setAnnouncementService(AnnouncementDAOImpl announcementService) {
        this.announcementService = announcementService;
    }

    public AnnouncementDAOImpl getAnnouncementService() {
        return announcementService;
    }
}

package com.springmvcproject.core.dao;

import com.springmvcproject.core.model.Announcement;
import org.springframework.stereotype.Repository;

/**
 * Created by yektan on 12.02.2017.
 */
@Repository
public class AnnouncementDAOImpl extends HibernateDAOImpl<Announcement,String>{
    public AnnouncementDAOImpl(){super(Announcement.class);}
    public AnnouncementDAOImpl(Class<Announcement> persistentClass) {
        super(persistentClass);
    }
}

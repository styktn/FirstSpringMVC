package com.springmvcproject.core.dao;

import com.springmvcproject.core.model.Menu;
import org.springframework.stereotype.Repository;

/**
 * Created by yektan on 29.12.2016.
 */
@Repository
public class MenuDAOImpl extends HibernateDAOImpl<Menu,String>{
    public MenuDAOImpl(){super(Menu.class);}
    public MenuDAOImpl(Class<Menu> persistentClass) {
        super(persistentClass);
    }
}

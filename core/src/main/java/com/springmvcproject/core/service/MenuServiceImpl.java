package com.springmvcproject.core.service;

import com.springmvcproject.core.dao.MenuDAOImpl;
import com.springmvcproject.core.dao.HibernateDAOImpl;
import com.springmvcproject.core.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by yektan on 29.12.2016.
 */
@org.springframework.stereotype.Service("menuService")
public class MenuServiceImpl extends HibernateServiceImpl<Menu, Integer>  {
    @Autowired
    private MenuDAOImpl menuDAO;

    public MenuServiceImpl() {
        super(Menu.class);
    }

    @PostConstruct
    public void init() {
        setHibernateDAO((HibernateDAOImpl<Menu,Integer>)((HibernateDAOImpl<?,?>)menuDAO));
    }

    public void setMenuDAO(MenuDAOImpl menuDAO) {
        this.menuDAO = menuDAO;
    }

    public MenuDAOImpl getMenuDAO() {
        return menuDAO;
    }
}

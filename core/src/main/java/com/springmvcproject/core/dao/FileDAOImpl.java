package com.springmvcproject.core.dao;

import com.springmvcproject.core.model.File;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by yektan on 14.02.2017.
 */
@Repository
public class FileDAOImpl extends HibernateDAOImpl<File,String>{
    public FileDAOImpl(){super(File.class);}
    public FileDAOImpl(Class<File> persistentClass) {
        super(persistentClass);
    }
}

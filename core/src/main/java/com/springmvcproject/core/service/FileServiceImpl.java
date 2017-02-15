package com.springmvcproject.core.service;

import com.springmvcproject.core.dao.FileDAOImpl;
import com.springmvcproject.core.dao.HibernateDAOImpl;
import com.springmvcproject.core.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.PostConstruct;

/**
 * Created by yektan on 14.02.2017.
 */
@Service("fileService")
public class FileServiceImpl extends HibernateServiceImpl<File, Integer>  {
    @Autowired
    FileDAOImpl fileDAO;
    private FileDAOImpl fileService;

    public FileServiceImpl() {
            super(File.class);
            }

    @PostConstruct
    public void init() {
            setHibernateDAO((HibernateDAOImpl<File,Integer>)((HibernateDAOImpl<?,?>)fileDAO));
            }

    public void setFileService(FileDAOImpl fileService) {
            this.fileService = fileService;
            }

    public FileDAOImpl getFileService() {
            return fileService;
            }
}

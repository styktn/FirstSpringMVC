package com.springmvcproject.core.account.service;

import com.springmvcproject.core.account.model.User;

/**
 * Created by yektan on 21.12.2016.
 */
public interface UserService {
    public void save(User user);
    public User findByUsername(String username);
}

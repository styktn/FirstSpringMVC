package com.springmvcproject.core.account.service;

/**
 * Created by yektan on 21.12.2016.
 */
public interface SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}

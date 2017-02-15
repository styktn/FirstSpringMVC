package com.springmvcproject.core.account.repository;

import com.springmvcproject.core.account.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Created by yektan on 21.12.2016.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}

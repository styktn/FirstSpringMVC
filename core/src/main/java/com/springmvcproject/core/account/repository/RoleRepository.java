package com.springmvcproject.core.account.repository;

import com.springmvcproject.core.account.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yektan on 21.12.2016.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}

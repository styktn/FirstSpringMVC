package com.springmvcproject.core.account.detail;

import com.springmvcproject.core.account.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Created by yektan on 6.02.2017.
 */
public class CustomUserDetail implements UserDetails {

        private static final long serialVersionUID = 1L;
        private User user;

        private Set<GrantedAuthority> authorities=null;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        public void setAuthorities(Set<GrantedAuthority> authorities)
        {
            this.authorities=authorities;
        }

        public String getPassword() {
            return user.getPassword();
        }

        public String getUsername() {
            return user.getUsername();
        }

        public boolean isAccountNonExpired() {
            return false;
        }

        public boolean isAccountNonLocked() {
            return true;
        }

        public boolean isCredentialsNonExpired() {
            return true;
        }

        public boolean isEnabled() {
            return true;
        }

    }

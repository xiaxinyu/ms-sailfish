package com.sailfish.authorization.bean;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

/**
 * @author XIAXINYU3
 */
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;
    private User user;

    public CustomUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
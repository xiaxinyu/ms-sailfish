package com.sailfish.authorization.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sailfish.authorization.bean.CustomUserDetails;
import com.sailfish.authorization.bean.User;
import com.sailfish.authorization.mapper.UsersMapper;
import com.sailfish.authorization.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * User Service Implements
 *
 * @author XIAXINYU3
 * @date 2019.7.1
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("Query user: userName={}", userName);
        User user = usersMapper.getUser(userName);
        if (null != user) {
            throw new UsernameNotFoundException(String.format("Can't find user, userName=%s", userName));
        }

        //用户权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (StringUtils.isNotBlank(user.getRoles())) {
            logger.info("用户具有权限，userName={}， roles={}", userName, user.getRoles());
            String[] roles = user.getRoles().split(",");
            for (String role : roles) {
                if (StringUtils.isNotBlank(role)) {
                    authorities.add(new SimpleGrantedAuthority(role.trim()));
                }
            }
        } else {
            throw new UsernameNotFoundException(String.format("User find roles, userName=%s", userName));
        }
        return new CustomUserDetails(user, authorities);
    }
}

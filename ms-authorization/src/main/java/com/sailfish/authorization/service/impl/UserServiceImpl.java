package com.sailfish.authorization.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sailfish.authorization.bean.CustomUserDetails;
import com.sailfish.authorization.bean.User;
import com.sailfish.authorization.mapper.UsersMapper;
import com.sailfish.authorization.service.UserService;

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
        return new CustomUserDetails(user);
    }
}

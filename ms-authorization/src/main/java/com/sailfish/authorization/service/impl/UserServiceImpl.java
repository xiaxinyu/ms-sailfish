package com.sailfish.authorization.service.impl;

import com.sailfish.authorization.mapper.UserMapper;
import com.sailfish.authorization.model.User;
import com.sailfish.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * User Service Implements
 *
 * @author XIAXINYU3
 * @date 2019.7.1
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUserName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}

package com.sailfish.authorization.service.impl;

import com.sailfish.authorization.bean.User;
import com.sailfish.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User Service Implements
 *
 * @author XIAXINYU3
 * @date 2019.7.1
 */
@Service
public class UserServiceImpl implements UserService {
    private List<User> userList;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>();
        userList.add(new User("summer", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("spring", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        userList.add(new User("winter", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        userList.add(new User("autumn", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> findUserList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }
}

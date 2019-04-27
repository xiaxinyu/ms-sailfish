package com.sailfish.authorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sailfish.authorization.bean.CustomUserDetails;
import com.sailfish.authorization.bean.User;
import com.sailfish.authorization.mapper.UsersMapper;
import com.sailfish.authorization.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = usersMapper.getUser(s);
		return new CustomUserDetails(user);
	}
}

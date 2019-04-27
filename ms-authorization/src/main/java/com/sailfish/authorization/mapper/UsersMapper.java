package com.sailfish.authorization.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sailfish.authorization.bean.User;

@Mapper
public interface UsersMapper {
	User getUser(String userName);
}

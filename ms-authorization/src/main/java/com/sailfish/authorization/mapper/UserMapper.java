package com.sailfish.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sailfish.authorization.model.User;


/**
 * @author XIAXINYU3
 * @date 2020.10.15
 */
public interface UserMapper extends BaseMapper<User> {

    User findUserByUserName(String username);

}

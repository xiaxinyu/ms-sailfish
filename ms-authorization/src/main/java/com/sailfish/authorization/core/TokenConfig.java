package com.sailfish.authorization.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author XIAXINYU3
 * @date 2020/10/16
 */
@Configuration
public class TokenConfig {
    @Autowired
    DataSource dataSource;

    //配置token的存储方法
    @Bean
    public TokenStore tokenStore() {
        //配置token存储在数据库
        return new JdbcTokenStore(dataSource);
    }
}

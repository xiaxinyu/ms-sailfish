package com.sailfish.authorization.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * Authorization Server configuration
 *
 * @author XIAXINYU3
 * @date 2019.7.1
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    TokenStore tokenStore;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Bean
    @Primary
    public AuthorizationServerTokenServices jdbcTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setSupportRefreshToken(true);

        //配置token的存储方法
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setAccessTokenValiditySeconds(300);
        tokenServices.setRefreshTokenValiditySeconds(1500);
        return tokenServices;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenServices(jdbcTokenServices());
        // 数据库管理授权码
        endpoints.authorizationCodeServices(new JdbcAuthorizationCodeServices(dataSource));
        // 数据库管理授权信息
        endpoints.approvalStore(new JdbcApprovalStore(dataSource));
    }
}

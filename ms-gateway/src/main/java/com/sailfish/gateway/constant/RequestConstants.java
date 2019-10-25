package com.sailfish.gateway.constant;

/**
 * @program: steam-gateway
 * @description: 请求常量类
 * @author: dushaohua5
 * @create: 2019-09-05 22:23
 */
public class RequestConstants {

    // 处理成功
    public static final Integer SUCCESS = 20000;
    // 处理失败
    public static final Integer FAILURE = 30000;
    // 无权限
    public static final Integer UNAUTHORIZED = 40000;

    public static final String HEADER_LABEL = "X-Eureka-Label";

    public static final String HEADER_JWT = "Jwt_Token";

    public static final String HEADER_TOKEN = "Authorization";

    // 是否需要执行过滤器
    public static final String SHOULD_FILTER = "should_filter";
}

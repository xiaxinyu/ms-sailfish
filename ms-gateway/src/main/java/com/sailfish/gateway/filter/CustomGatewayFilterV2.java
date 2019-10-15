package com.sailfish.gateway.filter;


import org.springframework.web.server.ServerWebExchange;

public interface CustomGatewayFilterV2 {

    /**
     * filter顺序，越小越先执行
     *
     * @return filter顺序
     */
    int filterOrder();

    /**
     * 是否执行
     *
     * @param exchange 请求上下文
     * @return true则执行，false不执行
     */
    boolean shouldFilter(ServerWebExchange exchange);

    /**
     * 执行方法
     *
     * @param exchange 请求上下文
     * @return true则继续执行后面的filter，false不再执行
     */
    boolean run(ServerWebExchange exchange);

}

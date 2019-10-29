package com.sailfish.gateway.filter;


import com.sailfish.gateway.domain.RequestContext;

/**
 * 自定义网关过滤器接口
 *
 * @author XIAXINYU3
 * @date 2019.10.25
 */
public interface CustomGlobalFilter {
    /**
     * filter顺序，越小越先执行
     *
     * @return filter顺序
     */
    int filterOrder();

    /**
     * 是否执行
     *
     * @param requestContext 请求上下文
     * @return true则执行，false不执行
     */
    boolean shouldFilter(RequestContext requestContext);

    /**
     * 执行方法
     *
     * @param requestContext 请求上下文
     * @return true则继续执行后面的filter，false不再执行
     */
    boolean run(RequestContext requestContext);
}

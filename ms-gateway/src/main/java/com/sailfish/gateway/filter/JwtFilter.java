package com.sailfish.gateway.filter;

import com.sailfish.gateway.constant.RequestConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

@Component
@Slf4j
public class JwtFilter implements CustomGatewayFilterV2 {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter(ServerWebExchange exchange) {
        return true;
    }

    @Override
    public boolean run(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(RequestConstants.HEADER_JWT);

        if (StringUtils.isEmpty(token)) {
            logger.info("Request get empty jwt , request uri: {} method: {}", exchange.getRequest().getURI(), exchange.getRequest().getMethodValue());
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Request get jwt , request uri: {} method: {} JWT: {}",
                        exchange.getRequest().getRemoteAddress(), exchange.getRequest().getMethodValue(), token);
            }
            //向headers中放jwt信息
            ServerHttpRequest host = exchange.getRequest().mutate().header(RequestConstants.HEADER_JWT, token).build();
            //将现在的request 变成 change对象
            exchange = exchange.mutate().request(host).build();
        }
        return true;
    }
}
package com.sailfish.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RootFilter implements GlobalFilter, Ordered {
    private static final String ACCESS_TOKEN_PREFIX = "bearer";

    private static final String ACCESS_TOKEN_PARAM = "access_token";

    private List<CustomGatewayFilterV2> customGatewayFilters;

    public RootFilter(Optional<List<CustomGatewayFilterV2>> optionalHelperFilters) {
        customGatewayFilters = optionalHelperFilters.orElseGet(Collections::emptyList)
                .stream()
                .sorted(Comparator.comparing(CustomGatewayFilterV2::filterOrder))
                .collect(Collectors.toList());
    }

    public void setCustomGatewayFilters(List<CustomGatewayFilterV2> customGatewayFilters) {
        this.customGatewayFilters = customGatewayFilters;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();

        String url = req.getURI() + req.getPath().contextPath().value();
        log.info("请求URL： url={}", url);
        for (CustomGatewayFilterV2 t : customGatewayFilters) {
            if (t.shouldFilter(exchange) && !t.run(exchange)) {
                break;
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 200;
    }
}
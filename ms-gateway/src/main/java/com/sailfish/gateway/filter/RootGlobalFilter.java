package com.sailfish.gateway.filter;

import com.sailfish.gateway.constant.CheckStatus;
import com.sailfish.gateway.constant.CheckStatusValidator;
import com.sailfish.gateway.domain.CheckRequest;
import com.sailfish.gateway.domain.CheckResponse;
import com.sailfish.gateway.domain.RequestContext;
import com.sailfish.gateway.domain.RequestVariableHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RootGlobalFilter implements GlobalFilter, Ordered {
    private static final String ACCESS_TOKEN_PREFIX = "bearer";
    private static final String ACCESS_TOKEN_PARAM = "access_token";
    private List<CustomGlobalFilter> customGatewayFilters;

    public RootGlobalFilter(Optional<List<CustomGlobalFilter>> optionalHelperFilters) {
        customGatewayFilters = optionalHelperFilters.orElseGet(Collections::emptyList)
                .stream()
                .sorted(Comparator.comparing(CustomGlobalFilter::filterOrder))
                .collect(Collectors.toList());
    }

    public void setCustomGatewayFilters(List<CustomGlobalFilter> customGatewayFilters) {
        this.customGatewayFilters = customGatewayFilters;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String uri = request.getURI().getPath();
        log.info("请求地址：{}", uri);

        //循环校验
        RequestContext requestContext = new RequestContext(parseAndGetRoute(exchange), new CheckRequest(parseAndGetToken(request),
                uri, request.getMethod().name().toLowerCase()), new CheckResponse(CheckStatus.SUCCESS_PASS_SITE));
        CheckResponse checkResponse = requestContext.getCheckResponse();
        try {
            for (CustomGlobalFilter t : customGatewayFilters) {
                if (t.shouldFilter(requestContext) && !t.run(requestContext)) {
                    break;
                }
            }
        } catch (Exception e) {
            checkResponse.setCheckStatus(CheckStatus.EXCEPTION_GATEWAY_HELPER);
            checkResponse.setMessage(String.format("网关验证发生异常: %s", e.getMessage()));
            log.info("网关验证发生异常: {}", e.getMessage());
        }

        //验证结果处理
        boolean validatedFlag = false;
        ServerHttpResponse response = exchange.getResponse();
        int checkStatusCode = checkResponse.getCheckStatus().getValue();
        if (CheckStatusValidator.SUCCESS.contains(checkStatusCode)) {
            log.debug("验证结果 200, context: {}", requestContext);
            validatedFlag = true;
        } else if (CheckStatusValidator.UNAUTHORIZED.contains(checkStatusCode)
                || CheckStatusValidator.FORBIDDEN.contains(checkStatusCode)
                || CheckStatusValidator.NOT_FOUND.contains(checkStatusCode)) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            log.info("验证结果 403, context: {}", requestContext);
        } else {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            log.info("验证结果 500, context: {}", requestContext);
        }

        //设置结果
        if (!StringUtils.isEmpty(checkResponse.getJwt())) {
            response.getHeaders().set(RequestVariableHolder.HEADER_JWT, checkResponse.getJwt());
        }
        if (!StringUtils.isEmpty(checkResponse.getMessage())) {
            response.getHeaders().set("request-message", checkResponse.getMessage());
        }
        response.getHeaders().set("request-status", checkResponse.getCheckStatus().name());
        response.getHeaders().set("request-code", checkResponse.getCheckStatus().getCode());

        //验证不通过，进行错误输出
        if (!validatedFlag) {
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            String message = checkResponse.getMessage();
            if (StringUtils.isEmpty(message)) {
                message = "Validation is fail in gateway";
            }
            DataBuffer buffer = response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 200;
    }

    private String parseAndGetToken(final ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        MultiValueMap<String, String> parameters = request.getQueryParams();

        String token = headers.getFirst(RequestVariableHolder.HEADER_TOKEN);
        if (StringUtils.isEmpty(token) && Objects.nonNull(parameters) && parameters.keySet().contains(ACCESS_TOKEN_PARAM)) {
            token = parameters.getFirst(ACCESS_TOKEN_PARAM);
        }
        if (!StringUtils.isEmpty(token)) {
            if (token.startsWith(ACCESS_TOKEN_PREFIX)) {
                token = token.replaceFirst("%20", " ");
            } else {
                token = ACCESS_TOKEN_PREFIX + " " + token;
            }
        }
        return token;
    }

    private Route parseAndGetRoute(ServerWebExchange exchange) {
        Object attribute = exchange.getAttribute("org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRoute");
        if (Objects.nonNull(attribute) && attribute instanceof Route) {
            return (Route) attribute;
        }
        return null;
    }
}
package com.sailfish.gateway.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * 请求上下文
 *
 * @author XIAXINYU3
 * @date 2019.10.25
 */
@Data
@ToString
public class RequestContext {
    public CheckRequest checkRequest;
    public CheckResponse checkResponse;
    private String requestKey;
    private RouteDefinition routeDefinition;
    private String trueUri;

    public RequestContext(CheckRequest checkRequest, CheckResponse checkResponse) {
        this.checkRequest = checkRequest;
        this.checkResponse = checkResponse;
    }
}


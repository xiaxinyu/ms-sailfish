package com.sailfish.gateway.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.cloud.gateway.route.Route;

/**
 * 请求上下文
 *
 * @author XIAXINYU3
 * @date 2019.10.25
 */
@Data
@ToString
public class RequestContext {
    public Route route;
    public CheckRequest checkRequest;
    public CheckResponse checkResponse;
    private String requestKey;
    private String trueUri;

    public RequestContext(Route route, CheckRequest checkRequest, CheckResponse checkResponse) {
        this.route = route;
        this.checkRequest = checkRequest;
        this.checkResponse = checkResponse;
    }
}


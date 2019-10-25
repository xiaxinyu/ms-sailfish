package com.sailfish.gateway.domain;

import lombok.Data;
import lombok.ToString;

/**
 * Check Request
 *
 * @author XIAXINYU3
 * @date 2019.10.25
 */
@Data
@ToString
public class CheckRequest {
    public final String uri;
    public final String method;
    public final String accessToken;

    public CheckRequest(String accessToken, String uri, String method) {
        this.accessToken = accessToken;
        this.uri = uri;
        this.method = method;
    }
}

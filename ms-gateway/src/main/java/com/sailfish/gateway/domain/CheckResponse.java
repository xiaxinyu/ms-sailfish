package com.sailfish.gateway.domain;

import com.sailfish.gateway.constant.CheckStatus;
import lombok.Data;
import lombok.ToString;

/**
 * Check Response
 *
 * @author XIAXINYU3
 * @date 2019.10.25
 */
@Data
@ToString
public class CheckResponse {
    private String jwt;
    private String message;
    private CheckStatus checkStatus;

    public CheckResponse() {
    }

    public CheckResponse(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }
}

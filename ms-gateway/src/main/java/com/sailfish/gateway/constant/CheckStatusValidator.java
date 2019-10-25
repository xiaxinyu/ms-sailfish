package com.sailfish.gateway.constant;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * 状态验证器
 *
 * @author XIAXINYU3
 * @date 2019.10.25
 */
public class CheckStatusValidator {
    public static final List<Integer> SUCCESS = new ArrayList<Integer>() {{
        add(HttpStatus.OK.value());
        add(HttpStatus.CREATED.value());
    }};

    public static final List<Integer> UNAUTHORIZED = new ArrayList<Integer>() {{
        add(HttpStatus.UNAUTHORIZED.value());
    }};

    public static final List<Integer> FORBIDDEN = new ArrayList<Integer>() {{
        add(HttpStatus.FORBIDDEN.value());
    }};

    public static final List<Integer> NOT_FOUND = new ArrayList<Integer>() {{
        add(HttpStatus.NOT_FOUND.value());
    }};
}

package com.sailfish.utils;

import org.springframework.util.AntPathMatcher;

public class AntPathMatcherUtils {
    private AntPathMatcherUtils() {
    }

    public static boolean match(String pattern, String value) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, value);
    }

    public static void main(String[] args) {
        System.out.println(match("/v1/projects/{project_id}/tasks/{taskId}/values", "/v1/projects/192/tasks/71/values"));
    }
}

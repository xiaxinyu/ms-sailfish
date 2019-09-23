package com.sailfish.utils;

import com.sailfish.exception.AppException;
import org.apache.commons.lang3.StringUtils;

public class SpringBootUtils {
    public static String formatJvmVaribles(String rawJvmVaribles) {
        if (StringUtils.isEmpty(rawJvmVaribles)) {
            throw new AppException("JVM varibles can't be blank");
        }

        final String separator = " ";
        String[] params = rawJvmVaribles.split(separator);
        for (int i = 0; i < params.length; i++) {
            params[i] = StringUtils.trim(params[i]);
        }

        final String commandJava = "java";
        final String commandNohup = "nohup";

        String[] newParams = new String[params.length + 2];
        newParams[0] = commandNohup;
        newParams[1] = commandJava;
        System.arraycopy(params, 0, newParams, 2, params.length);
        String newJvmVaribles = StringUtils.join(newParams, separator);

        return newJvmVaribles;
    }

    public static void main(String[] args) {
        String test = "nohup -XX:MaxMetaspaceSize  =          1024m";
        System.out.println(formatJvmVaribles(test));
    }
}

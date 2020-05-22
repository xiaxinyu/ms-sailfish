package com.sailfish.string;

import com.sailfish.utils.JSchUtils;

import java.util.Map;

public class JSchUtilsTest2 {
    public static void main(String[] args) throws Exception {
        Map<String, String> map = JSchUtils.load("C:\\test", "test");
        System.out.println("PRIVATE_KEY:");
        System.out.println(map.get(JSchUtils.PRIVATE_KEY));
        System.out.println();
        System.out.println("PUBLIC_KEY:");
        System.out.println(map.get(JSchUtils.PUBLIC_KEY));
    }
}

package com.sailfish.utils;

import java.util.Base64;

/**
 * Base64 Utils
 *
 * @author XIAXINYU3
 * @date 2019.7.2
 */
public class Base64Utils {
    private Base64Utils() {
    }

    public static String getDataFromBase64(String s) {
        if (s == null) {
            return null;
        }
        try {
            byte[] decode = Base64.getDecoder().decode(s);
            return new String(decode);
        } catch (Exception e) {
            return null;
        }
    }
}

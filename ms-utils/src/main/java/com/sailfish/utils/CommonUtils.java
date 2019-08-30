package com.sailfish.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Common utils
 *
 * @author XIAXINYU3
 * @date 2019.7.2
 */
public class CommonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);

    private CommonUtils() {
    }

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }
}

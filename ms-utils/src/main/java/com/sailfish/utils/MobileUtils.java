package com.sailfish.utils;

import com.sailfish.enumeration.BrowserEnum;
import org.apache.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * Mobile Utils
 *
 * @author XIAXINYU3
 * @date 2019.7.2
 */
public class MobileUtils {
    private MobileUtils() {
    }

    public static boolean isMobileDevice(HttpServletRequest request) {
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        for (BrowserEnum browserEnum : BrowserEnum.values()) {
            if (!BrowserEnum.PC.name().equals(browserEnum.name())) {
                for (String userAgentFromEnum : browserEnum.userAgents()) {
                    if (userAgent.contains(userAgentFromEnum)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isAndroidDevice(HttpServletRequest request) {
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        for (String mobileDeviceType : BrowserEnum.ANDROID.userAgents()) {
            if (userAgent.contains(mobileDeviceType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIOSDevice(HttpServletRequest request) {
        if (isAndroidDevice(request)) {
            return false;
        }
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        for (String mobileDeviceType : BrowserEnum.IOS.userAgents()) {
            if (userAgent.contains(mobileDeviceType)) {
                return true;
            }
        }
        return false;
    }
}

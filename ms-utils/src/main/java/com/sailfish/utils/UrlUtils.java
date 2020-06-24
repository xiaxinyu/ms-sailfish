package com.sailfish.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Enumeration;

/**
 * URL Utils
 *
 * @author XIAXINYU3
 */
public class UrlUtils {
    private static final Logger logger = LoggerFactory.getLogger(UrlUtils.class);
    private static String ipAddress;
    private static final String LOCAL_HOST_IP = "127.0.0.1";

    public static String getServerIpAddress() {
        if (StringUtils.isNotEmpty(ipAddress)) {
            return ipAddress;
        } else {
            ipAddress = fetchServerIP();
            return ipAddress;
        }
    }

    private static String fetchServerIP() {
        Enumeration<NetworkInterface> allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !LOCAL_HOST_IP.equals(ip.getHostAddress())) {
                        logger.info("Host IP address is {}", ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            logger.error("Get host IP address Exception {}", e.getMessage());
        }
        logger.error("Can not get host IP address.");
        return null;
    }

    public static boolean isExistingUrl(String url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            logger.info("URL {} does not exist", url);
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static String encodingUrl(String value) throws UnsupportedEncodingException {
        String newValue = URLEncoder.encode(value, "utf-8");
        return newValue;
    }
}

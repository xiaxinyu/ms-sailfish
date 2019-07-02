package com.sailfish.exception;

/**
 * Application Exception
 *
 * @author XIAXINYU3
 * @date 2019.7.2
 */
public class AppException extends RuntimeException {
    public AppException(String description) {
        super(description);
    }
}

package com.sailfish.state.machine.enumeration;

/**
 * CheckTypeEnum
 *
 * @author XIAXINYU3
 * @date 2020.1.16
 */
public enum CheckTypeEnum {
    ACCOUNT_OPEN("account_open");

    private String code;

    CheckTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

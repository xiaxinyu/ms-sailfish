package com.sailfish.enumeration;


/**
 * Browser Enumeration
 *
 * @author XIAXINYU3
 * @date 2019.7.2
 */
public enum BrowserEnum {

    ANDROID(new String[]{"Android"}, "tel", "tel"),
    IOS(new String[]{"iPhone", "iPad", "iPod"}, "tel", "text"),
    OTHER_MOBILES(new String[]{"SymbianOS", "Windows Phone"}, "text", "text"),
    PC(new String[]{"Linux", "Unix", "Windows"}, "text", "text");

    private String[] userAgents;
    private String inputTypeDigit;
    private String inputTypeDigitAndSymbol;


    BrowserEnum(String[] userAgents, String inputTypeDigit, String inputTypeDigitAndSymbol) {
        this.userAgents = userAgents;
        this.inputTypeDigit = inputTypeDigit;
        this.inputTypeDigitAndSymbol = inputTypeDigitAndSymbol;
    }

    public String[] userAgents() {
        return this.userAgents;
    }

    public String inputTypeDigit() {
        return this.inputTypeDigit;
    }

    public String inputTypeDigitAndSymbol() {
        return this.inputTypeDigitAndSymbol;
    }
}

package com.fehead.initialize.login;

/**
 * @author lmwis on 2019-07-21 16:29
 */
public enum  LoginType {

    BY_MOBILE_PASSWORD(20001,"mobilePassword"),

    BY_EMAIL_PASSWORD(20002,"emailPassword"),

    BY_MOBILE_CODE(20003,"mobileCode");

    private String typeStr;
    private int code;

    LoginType(int code, String typeStr) {

        this.code = code;

        this.typeStr = typeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

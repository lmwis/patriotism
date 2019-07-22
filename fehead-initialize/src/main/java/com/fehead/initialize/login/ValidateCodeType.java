package com.fehead.initialize.login;

/**
 * @author lmwis on 2019-07-21 16:59
 */
public class ValidateCodeType {

    private String code;

    private String type;

    public ValidateCodeType(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

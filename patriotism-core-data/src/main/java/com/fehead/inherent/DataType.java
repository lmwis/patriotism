package com.fehead.inherent;

/**
 * @author lmwis on 2019-08-12 17:28
 */
public enum DataType {

    DATA_VIDEO(102,"视频"),
    DATA_ARTICLE(101,"文章");

    private int code;
    private String str;

    DataType(int code, String str) {
        this.code = code;
        this.str = str;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

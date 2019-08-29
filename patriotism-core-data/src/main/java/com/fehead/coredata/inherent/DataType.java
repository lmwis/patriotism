package com.fehead.coredata.inherent;

/**
 * @author lmwis on 2019-08-12 17:28
 */
public enum DataType {

    DATA_ARTICLE(1,101,"文章"),
    DATA_VIDEO(2,102,"视频");


    private int id;
    private int code;
    private String str;

    DataType(int id,int code, String str) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

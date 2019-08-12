package com.fehead.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author lmwis on 2019-08-12 16:18
 */

@TableName("tags")
public class Tag {

    private int id;

    private int code;

    private String str;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

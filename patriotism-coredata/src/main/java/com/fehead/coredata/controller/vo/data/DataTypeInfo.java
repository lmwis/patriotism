package com.fehead.coredata.controller.vo.data;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 17:52
 * @Version 1.0
 */
public class DataTypeInfo {

    private int id;

    private int actual_id;

    private int type_code;

    private String type_str;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_code() {
        return type_code;
    }

    public void setType_code(int type_code) {
        this.type_code = type_code;
    }

    public String getType_str() {
        return type_str;
    }

    public void setType_str(String type_str) {
        this.type_str = type_str;
    }

    public int getActual_id() {
        return actual_id;
    }

    public void setActual_id(int actual_id) {
        this.actual_id = actual_id;
    }
}

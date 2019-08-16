package com.fehead.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:14
 * @Version 1.0
 */
@TableName("data_collect")
public class Data {

    @TableId
    private int id;

    private int actualId;

    private int typeId;

    private Date datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActualId() {
        return actualId;
    }

    public void setActualId(int actualId) {
        this.actualId = actualId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}

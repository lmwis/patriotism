package com.fehead.coredata.dao;

import java.util.Date;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 18:40
 * @Version 1.0
 */
public class Data {

    private String title;

    private String des;

    private String imageUrl;

    private String author;

    private Date datetime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}

package com.fehead.controller.vo.data;

import com.fehead.controller.vo.TagDisplay;

import java.util.Date;
import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 16:46
 * @Version 1.0
 */
public class DataDisplayInfo {

    private int id;

    private String des;

    private String title;

    private List<TagDisplay> tags;

    private Date upload_time;

    private String image;

    private String author;

    private int type_code;

    private String type_str;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TagDisplay> getTags() {
        return tags;
    }

    public void setTags(List<TagDisplay> tags) {
        this.tags = tags;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}

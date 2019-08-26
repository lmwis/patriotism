package com.fehead.controller.vo;

import java.util.Date;
import java.util.List;

/**
 * @author lmwis on 2019-08-12 09:50
 */
public class VideoDisplayInfo {

    private int id;

    private String video_des;

    private String video_title;

    private List<TagDisplay> video_tags;

    private Date video_upload_time;

    private String video_image;

    private String video_author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_des() {
        return video_des;
    }

    public void setVideo_des(String video_des) {
        this.video_des = video_des;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public List<TagDisplay> getVideo_tags() {
        return video_tags;
    }

    public void setVideo_tags(List<TagDisplay> video_tags) {
        this.video_tags = video_tags;
    }

    public Date getVideo_upload_time() {
        return video_upload_time;
    }

    public void setVideo_upload_time(Date video_upload_time) {
        this.video_upload_time = video_upload_time;
    }

    public String getVideo_image() {
        return video_image;
    }

    public void setVideo_image(String video_image) {
        this.video_image = video_image;
    }

    public String getVideo_author() {
        return video_author;
    }

    public void setVideo_author(String video_author) {
        this.video_author = video_author;
    }
}

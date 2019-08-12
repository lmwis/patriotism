package com.fehead.controller.vo;

import java.util.List;

/**
 * @author lmwis on 2019-08-12 15:59
 */
public class VideoListDisplayInfo {

    private int page;

    private int type_code;

    private String type_str;

    private List<VideoDisplayInfo> video_lists;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public List<VideoDisplayInfo> getVideo_lists() {
        return video_lists;
    }

    public void setVideo_lists(List<VideoDisplayInfo> video_lists) {
        this.video_lists = video_lists;
    }
}

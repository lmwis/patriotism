package com.fehead.coredata.controller.vo.data.video;

import com.fehead.coredata.controller.vo.data.DataListDisplayInfo;

import java.util.List;

/**
 * 首页列表视频类型数据项展示
 * @author lmwis on 2019-08-12 15:59
 */

public class VideoListDisplayInfo extends DataListDisplayInfo {


    private List<VideoDisplayInfo> video_lists;

    private int type_code;

    private String type_str;

    public List<VideoDisplayInfo> getVideo_lists() {
        return video_lists;
    }

    public void setVideo_lists(List<VideoDisplayInfo> video_lists) {
        this.video_lists = video_lists;
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


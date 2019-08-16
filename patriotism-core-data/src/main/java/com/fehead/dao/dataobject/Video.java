package com.fehead.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fehead.dao.Data;

import java.util.Date;

/**
 * @author lmwis on 2019-08-11 20:09
 */

@TableName("video_info")
public class Video extends Data {

    @TableId("id")
    private int videoId;

    private String videoPath;

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }



}

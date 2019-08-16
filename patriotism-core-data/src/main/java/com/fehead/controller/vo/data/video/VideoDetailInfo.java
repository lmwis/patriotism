package com.fehead.controller.vo.data.video;

import com.fasterxml.jackson.annotation.JsonView;
import com.fehead.response.FeheadResponse;

import java.util.Date;

/**
 *
 * 视频详情
 *  extend VideoDisplayInfo
 *  implements FeheadResponse
 *
 * 写代码 敲快乐
 * だからよ...止まるんじゃねぇぞ
 * ▏n
 * █▏　､⺍
 * █▏ ⺰ʷʷｨ
 * █◣▄██◣
 * ◥██████▋
 * 　◥████ █▎
 * 　　███▉ █▎
 * 　◢████◣⌠ₘ℩
 * 　　██◥█◣\≫
 * 　　██　◥█◣
 * 　　█▉　　█▊
 * 　　█▊　　█▊
 * 　　█▊　　█▋
 * 　　 █▏　　█▙
 * 　　 █
 *
 * @author Nightnessss 2019/8/14 11:45
 */
public class VideoDetailInfo extends VideoDisplayInfo implements FeheadResponse {

    public interface VideoTypeView {};

    private String video_url;

    @JsonView(VideoTypeView.class)
    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    @Override
    @JsonView(VideoTypeView.class)
    public int getId() {
        return super.getId();
    }

    @Override
    @JsonView(VideoTypeView.class)
    public String getVideo_title() {
        return super.getVideo_title();
    }

    @Override
    @JsonView(VideoTypeView.class)
    public Date getVideo_upload_time() {
        return super.getVideo_upload_time();
    }

    @Override
    @JsonView(VideoTypeView.class)
    public String getVideo_author() {
        return super.getVideo_author();
    }
}

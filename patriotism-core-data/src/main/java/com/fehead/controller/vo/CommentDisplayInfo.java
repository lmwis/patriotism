package com.fehead.controller.vo;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-14 20:25
 * @Version 1.0
 */
public class CommentDisplayInfo {

    private int user_id;

    private String user_avater;

    private String user_name;

    private String comment_content;

    private int like_num;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_avater() {
        return user_avater;
    }

    public void setUser_avater(String user_avater) {
        this.user_avater = user_avater;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }
}

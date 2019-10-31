package com.fehead.coredata.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-17 09:11
 * @Version 1.0
 */
@TableName("ifLike")
public class IfLike {

    @TableId
    private int id;

    private int userId;

    private int commentId;

    // 点赞状态 0不点赞，1点赞
    private int isLike;

    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public IfLike() {
    }

    public IfLike(int userId, int commentId, int isLike, Date updateTime) {
        this.userId = userId;
        this.commentId = commentId;
        this.isLike = isLike;
        this.updateTime = updateTime;
    }
}

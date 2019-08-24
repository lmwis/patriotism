package com.fehead.coredata.controller.vo.data.article;

import com.fehead.coredata.controller.vo.TagDisplay;

import java.util.Date;
import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 16:00
 * @Version 1.0
 */
public class ArticleDisplayInfo {

    private int id;

    private String article_des;

    private String article_title;

    private List<TagDisplay> article_tags;

    private Date article_upload_time;

    private String article_image;

    private String article_author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle_des() {
        return article_des;
    }

    public void setArticle_des(String article_des) {
        this.article_des = article_des;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public List<TagDisplay> getArticle_tags() {
        return article_tags;
    }

    public void setArticle_tags(List<TagDisplay> article_tags) {
        this.article_tags = article_tags;
    }

    public Date getArticle_upload_time() {
        return article_upload_time;
    }

    public void setArticle_upload_time(Date article_upload_time) {
        this.article_upload_time = article_upload_time;
    }

    public String getArticle_image() {
        return article_image;
    }

    public void setArticle_image(String article_image) {
        this.article_image = article_image;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }
}

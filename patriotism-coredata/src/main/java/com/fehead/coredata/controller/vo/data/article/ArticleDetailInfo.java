package com.fehead.coredata.controller.vo.data.article;

import com.fasterxml.jackson.annotation.JsonView;
import com.fehead.coredata.response.FeheadResponse;

import java.util.Date;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 16:17
 * @Version 1.0
 */
public class ArticleDetailInfo extends ArticleDisplayInfo implements FeheadResponse {

    public interface ArticleTypeView {};

    private String article_content;

    @JsonView(ArticleTypeView.class)
    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    @Override
    @JsonView(ArticleTypeView.class)
    public int getId() {
        return super.getId();
    }

    @Override
    @JsonView(ArticleTypeView.class)
    public String getArticle_title() {
        return super.getArticle_title();
    }

    @Override
    @JsonView(ArticleTypeView.class)
    public Date getArticle_upload_time() {
        return super.getArticle_upload_time();
    }

    @Override
    @JsonView(ArticleTypeView.class)
    public String getArticle_author() {
        return super.getArticle_author();
    }

    @Override
    @JsonView(ArticleTypeView.class)
    public String getArticle_image() {
        return super.getArticle_image();
    }
}

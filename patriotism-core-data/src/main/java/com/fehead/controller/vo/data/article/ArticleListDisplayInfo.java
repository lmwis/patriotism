package com.fehead.controller.vo.data.article;

import com.fehead.controller.vo.data.DataListDisplayInfo;

import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:57
 * @Version 1.0
 */
public class ArticleListDisplayInfo extends DataListDisplayInfo {

    private List<ArticleDisplayInfo> article_lists;

    private int type_code;

    private String type_str;

    public List<ArticleDisplayInfo> getArticle_lists() {
        return article_lists;
    }

    public void setArticle_lists(List<ArticleDisplayInfo> article_lists) {
        this.article_lists = article_lists;
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

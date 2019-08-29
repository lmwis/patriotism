package com.fehead.coredata.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fehead.coredata.dao.Data;

/**
 * @author lmwis on 2019-08-12 09:12
 */

@TableName("article_info")
public class Article extends Data {

    @TableId("id")
    private int articleId;


    //据说64的jvm中 string能存2G文本
    private String context;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}

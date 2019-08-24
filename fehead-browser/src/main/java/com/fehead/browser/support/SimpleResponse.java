package com.fehead.browser.support;

/**
 * @author lmwis on 2019-07-16 10:25
 */
public class SimpleResponse {
    public Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}

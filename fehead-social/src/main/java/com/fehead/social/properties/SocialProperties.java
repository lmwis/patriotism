package com.fehead.social.properties;

/**
 * @author lmwis on 2019-07-18 20:22
 */
public class SocialProperties {

    private QQProperties qq = new QQProperties();

    private String filterProcessorUrl = "/auth";

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessorUrl() {
        return filterProcessorUrl;
    }

    public void setFilterProcessorUrl(String filterProcessorUrl) {
        this.filterProcessorUrl = filterProcessorUrl;
    }
}

package com.fehead.social.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author lmwis on 2019-07-18 20:21
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}

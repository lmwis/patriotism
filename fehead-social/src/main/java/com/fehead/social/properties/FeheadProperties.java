package com.fehead.social.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lmwis on 2019-07-18 20:24
 */
@ConfigurationProperties(prefix = "fehead")
public class FeheadProperties {

    private SocialProperties social = new SocialProperties();

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}

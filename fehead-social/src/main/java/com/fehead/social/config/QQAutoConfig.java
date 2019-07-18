package com.fehead.social.config;

import com.fehead.social.properties.FeheadProperties;
import com.fehead.social.properties.QQProperties;
import com.fehead.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author lmwis on 2019-07-18 20:26
 */
@Configuration
@ConditionalOnProperty(prefix = "fehead.social.qq",name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    FeheadProperties feheadProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        QQProperties qqProperties = feheadProperties.getSocial().getQq();

        return new QQConnectionFactory(qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret());
    }
}

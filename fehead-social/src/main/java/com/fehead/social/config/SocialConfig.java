package com.fehead.social.config;

import com.fehead.social.FeheadSpringSocialConfigurer;
import com.fehead.social.properties.FeheadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


/**
 * @author lmwis on 2019-07-18 19:47
 */
@Configuration
@EnableSocial
@EnableConfigurationProperties(FeheadProperties.class) //使配置读取器生效
public class SocialConfig  extends SocialConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Autowired
    FeheadProperties feheadProperties;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
        jdbcUsersConnectionRepository.setTablePrefix("lmwis_");
        return jdbcUsersConnectionRepository;
    }

    @Bean
    public SpringSocialConfigurer feheadSocialConfig(){

        FeheadSpringSocialConfigurer configurer = new FeheadSpringSocialConfigurer(feheadProperties.getSocial().getFilterProcessorUrl());

        return configurer;
    }
}

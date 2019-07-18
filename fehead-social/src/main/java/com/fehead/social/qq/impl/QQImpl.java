package com.fehead.social.qq.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fehead.social.qq.QQ;
import com.fehead.social.qq.QQUserInfo;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author lmwis on 2019-07-18 11:23
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    Logger logger = LoggerFactory.getLogger(QQImpl.class);

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/oauth2.0/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;

        String url = String.format(URL_GET_OPENID,accessToken);

        String reuslt = getRestTemplate().getForObject(url,String.class);

        logger.info(reuslt);

        this.openId = StringUtils.substringBetween(reuslt,"\"openid\"","}");

    }

    @Override
    public QQUserInfo getUserInfo() {

        String url = String.format(URL_GET_USERINFO,appId,openId);

        String result = getRestTemplate().getForObject(url,String.class);


        QQUserInfo qqUserInfo = objectMapper.convertValue(result,QQUserInfo.class);


        return qqUserInfo;
    }


}

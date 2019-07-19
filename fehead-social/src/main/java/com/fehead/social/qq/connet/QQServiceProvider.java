package com.fehead.social.qq.connet;

import com.fehead.social.qq.QQ;
import com.fehead.social.qq.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author lmwis on 2019-07-18 19:21
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appid;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String clientId,String clientSecret) {
        super(new QQOAuth2Template(clientId,clientSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    /**
     * 不能交给Spring管理因为会被创建为单例连接
     * @param accessToken
     * @return
     */
    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appid);
    }
}

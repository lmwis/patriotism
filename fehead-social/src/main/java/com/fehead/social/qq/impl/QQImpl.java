package com.fehead.social.qq.impl;

import com.fehead.social.qq.QQ;
import com.fehead.social.qq.QQUserInfo;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 * @author lmwis on 2019-07-18 11:23
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    @Override
    public QQUserInfo getUserInfo() {
        return null;
    }


}

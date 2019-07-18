package com.fehead.social.qq.connet;

import com.fehead.social.qq.QQ;
import com.fehead.social.qq.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author lmwis on 2019-07-18 19:32
 */
public class QQAdapter implements ApiAdapter<QQ>{

    /**
     * 测试当前api是否可用
     * @param api
     * @return
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();

        values.setDisplayName(userInfo.getNickname());

        values.setImageUrl(userInfo.getFigureurl_qq_1());

        values.setProfileUrl(null);

        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        //do nothing
    }
}

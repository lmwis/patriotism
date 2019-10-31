package com.fehead.initialize.properties;

import com.fehead.initialize.service.model.SmsModel;

import java.util.List;

/**
 * 写代码 敲快乐
 * だからよ...止まるんじゃねぇぞ
 * ▏n
 * █▏　､⺍
 * █▏ ⺰ʷʷｨ
 * █◣▄██◣
 * ◥██████▋
 * 　◥████ █▎
 * 　　███▉ █▎
 * 　◢████◣⌠ₘ℩
 * 　　██◥█◣\≫
 * 　　██　◥█◣
 * 　　█▉　　█▊
 * 　　█▊　　█▊
 * 　　█▊　　█▋
 * 　　 █▏　　█▙
 * 　　 █
 *
 * @author Nightnessss 2019/7/20 17:13
 */
public class SmsProperties {

    private String loginPreKeyInRedis="fehead-patriotism-sms-login-";
    private String registerPreKeyInRedis="fehead-patriotism-sms-register-";
    private String resetPreKeyInRedis="fehead-patriotism-sms-reset-";
    private String appKey;
    private String secret;
    private String regionId;
    private String signName;
    private List<SmsModel> smsModel;
    private Integer smsNumber;

    public String getResetPreKeyInRedis() {
        return resetPreKeyInRedis;
    }

    public void setResetPreKeyInRedis(String resetPreKeyInRedis) {
        this.resetPreKeyInRedis = resetPreKeyInRedis;
    }

    public String getLoginPreKeyInRedis() {
        return loginPreKeyInRedis;
    }

    public void setLoginPreKeyInRedis(String loginPreKeyInRedis) {
        this.loginPreKeyInRedis = loginPreKeyInRedis;
    }

    public String getRegisterPreKeyInRedis() {
        return registerPreKeyInRedis;
    }

    public void setRegisterPreKeyInRedis(String registerPreKeyInRedis) {
        this.registerPreKeyInRedis = registerPreKeyInRedis;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public List<SmsModel> getSmsModel() {
        return smsModel;
    }

    public void setSmsModel(List<SmsModel> smsModel) {
        this.smsModel = smsModel;
    }

    public Integer getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(Integer smsNumber) {
        this.smsNumber = smsNumber;
    }
}

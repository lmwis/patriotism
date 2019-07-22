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

    private String appKey;
    private String secret;
    private String regionId;
    private String signName;
    private List<SmsModel> smsModel;
    private Integer smsNumber;

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

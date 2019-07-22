package com.fehead.initialize.service.model;

import org.apache.commons.lang3.time.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * @author Nightnessss 2019/7/19 17:21
 */
public class ValidateCode implements Serializable {
    private String telphone;

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode() {
    }


    public ValidateCode(String telphone, String code) {
        this.telphone = telphone;
        this.code = code;
        this.expireTime = LocalDateTime.now();
    }

    public boolean isExpired(Integer seconds) {
        return LocalDateTime.now().isAfter(this.getExpireTime().plusSeconds(seconds));
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

}

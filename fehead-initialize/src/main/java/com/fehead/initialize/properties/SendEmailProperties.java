package com.fehead.initialize.properties;

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
 * @author Nightnessss 2019/7/23 12:06
 */
public class SendEmailProperties {

    private String fromAddress = "nightnessss@163.com";

    private String emailValidatePreKeyInRedis="fehead-patriotism-email-validate-";

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String formAddress) {
        this.fromAddress = formAddress;
    }

    public String getEmailValidatePreKeyInRedis() {
        return emailValidatePreKeyInRedis;
    }

    public void setEmailValidatePreKeyInRedis(String emailValidatePreKeyInRedis) {
        this.emailValidatePreKeyInRedis = emailValidatePreKeyInRedis;
    }
}

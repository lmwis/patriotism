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
 * @author Nightnessss 2019/7/16 21:01
 */
public class BrowserProperties {
    private String loginPage = "/signIn.html";

    private String formLoginUrl = "/loginByForm";

    private String otpLoginUrl = "/loginByOtp";

    private String sendOtpCode = "/send/otpCode";

    private String telParameter = "tel";

    private String codeParameter = "code";

    public String getTelParameter() {
        return telParameter;
    }

    public String getCodeParameter() {
        return codeParameter;
    }

    public void setCodeParameter(String codeParameter) {
        this.codeParameter = codeParameter;
    }

    public void setTelParameter(String telParameter) {
        this.telParameter = telParameter;
    }

    public String getSendOtpCode() {
        return sendOtpCode;
    }

    public void setSendOtpCode(String sendOtpCode) {
        this.sendOtpCode = sendOtpCode;
    }

    public String getFormLoginUrl() {
        return formLoginUrl;
    }

    public void setFormLoginUrl(String formLoginUrl) {
        this.formLoginUrl = formLoginUrl;
    }

    public String getOtpLoginUrl() {
        return otpLoginUrl;
    }

    public void setOtpLoginUrl(String otpLoginUrl) {
        this.otpLoginUrl = otpLoginUrl;
    }

    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}

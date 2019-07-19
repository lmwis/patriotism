package com.fehead.social.properties;

/**
 * @author lmwis on 2019-07-16 10:29
 */
public class BrowserProperties {
    private String loginPage = "/lmwis-signIn.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds=3600;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}

package com.fehead.initialize.login.config;

import com.fehead.initialize.login.TelValidateCodeAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author lmwis on 2019-07-22 10:26
 */
@Configuration("feheadWebSecurityConfig")
public class FeheadWebSecurityConfig<H extends HttpSecurityBuilder<H>>
        extends AbstractAuthenticationFilterConfigurer<H, FeheadWebSecurityConfig<H>, TelValidateCodeAuthenticationFilter> {

    /**
     * Creates a new instance
     *
     */
    protected FeheadWebSecurityConfig() {
        super(new TelValidateCodeAuthenticationFilter(), null);
        telParameter("tel");
        codeParameter("code");
    }

    private FeheadWebSecurityConfig<H> codeParameter(String codeParameter) {
        getAuthenticationFilter().setCodeParameter(codeParameter);
        return this;
    }

    private FeheadWebSecurityConfig<H> telParameter(String telParameter) {
        getAuthenticationFilter().setTelParameter(telParameter);
        return this;
    }

    /**
     * 配置登陆页面
     * @param loginPage
     * @return
     */
    @Override
    public FeheadWebSecurityConfig<H> loginPage(String loginPage) {
        return super.loginPage(loginPage);
    }

    /**
     * Forward Authentication Failure Handler
     *
     * @param forwardUrl the target URL in case of failure
     * @return he {@link FormLoginConfigurer} for additional customization
     */
    public FeheadWebSecurityConfig<H> failureForwardUrl(String forwardUrl) {
        failureHandler(new ForwardAuthenticationFailureHandler(forwardUrl));
        return this;
    }

    /**
     * Forward Authentication Success Handler
     *
     * @param forwardUrl the target URL in case of success
     * @return he {@link FormLoginConfigurer} for additional customization
     */
    public FeheadWebSecurityConfig<H> successForwardUrl(String forwardUrl) {
        successHandler(new ForwardAuthenticationSuccessHandler(forwardUrl));
        return this;
    }

    @Override
    public void init(H http) throws Exception {
        super.init(http);
        initDefaultLoginFilter(http);
    }


    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.config.annotation.web.configurers.
     * AbstractAuthenticationFilterConfigurer
     * #createLoginProcessingUrlMatcher(java.lang.String)
     */
    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }

    private void initDefaultLoginFilter(H http) {

        DefaultLoginPageGeneratingFilter loginPageGeneratingFilter = http
                .getSharedObject(DefaultLoginPageGeneratingFilter.class);
        if (loginPageGeneratingFilter != null && !isCustomLoginPage()) {
            loginPageGeneratingFilter.setFormLoginEnabled(true);
            loginPageGeneratingFilter.setUsernameParameter(getTelParameter());
            loginPageGeneratingFilter.setPasswordParameter(getCodeParameter());
            loginPageGeneratingFilter.setLoginPageUrl(getLoginPage());
            loginPageGeneratingFilter.setFailureUrl(getFailureUrl());
            loginPageGeneratingFilter.setAuthenticationUrl(getLoginProcessingUrl());
        }

    }

    private String getCodeParameter() {
        return this.getAuthenticationFilter().getCodeParameter();
    }

    private String getTelParameter() {
        return this.getAuthenticationFilter().getTelParameter();
    }

}

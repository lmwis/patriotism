package com.fehead.initialize.login.tel.code.authentication;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmwis on 2019-07-21 10:01
 */
public class TelValidateCodeAuthenticationFilter
        extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_TEL_KEY = "tel";
    public static final String SPRING_SECURITY_FORM_CODE_KEY = "code";
    public static final String SPRING_SECURITY_FORM_TYPE_KEY = "type";

    private String telParameter = SPRING_SECURITY_FORM_TEL_KEY;
    private String codeParameter = SPRING_SECURITY_FORM_CODE_KEY;
    private String typeParameter = SPRING_SECURITY_FORM_TYPE_KEY;

    private AuthenticationSuccessHandler successHandler ;
    private AuthenticationFailureHandler failureHandler ;

    private boolean postOnly = true;


    public TelValidateCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/loginByOtp", "POST"));
    }

    public TelValidateCodeAuthenticationFilter(String url) {
        super(new AntPathRequestMatcher(url, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        //获取请求中的参数
        String tel = obtainTel(request);
        String code = obtainCode(request);

        if (tel == null) {
            tel = "";
        }

        if (code == null) {
            code = "";
        }

        tel = tel.trim();

        //创建未验证的token
        TelValidateCodeAuthenticationToken authRequest = new TelValidateCodeAuthenticationToken(tel,code);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        //校验后的token
        Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
        return authentication;
    }

    private String obtainType(HttpServletRequest request) {
        return request.getParameter(typeParameter);
    }

    private void setDetails(HttpServletRequest request, TelValidateCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainCode(HttpServletRequest request) {
        return request.getParameter(codeParameter);
    }

    private String obtainTel(HttpServletRequest request) {
        return request.getParameter(telParameter);
    }


    public boolean isPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getTelParameter() {
        return telParameter;
    }

    public void setTelParameter(String telParameter) {
        this.telParameter = telParameter;
    }

    public String getCodeParameter() {
        return codeParameter;
    }

    public void setCodeParameter(String codeParameter) {
        this.codeParameter = codeParameter;
    }

    @Override
    public AuthenticationSuccessHandler getSuccessHandler() {
        return successHandler;
    }

    /**
     * 设置给父类的handler，因为调用链中会调用父类中的handler
     * @param successHandler
     */
    public void setSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }

    @Override
    public AuthenticationFailureHandler getFailureHandler() {
        return failureHandler;
    }

    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        // 如果手动调用则会与父类的handler起冲突，被父类默认的
        // SavedRequestAwareAuthenticationSuccessHandler重定向
        // 从而产生302错误
//        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        // 同上
//        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}

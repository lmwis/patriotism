package com.fehead.initialize.login;

import com.fehead.initialize.error.BusinessExpection;
import com.fehead.initialize.error.EmBusinessError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmwis on 2019-07-21 10:01
 */
//@Component("telValidateCodeAuthenticationFilter")
public class TelValidateCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_TEL_KEY = "tel";
    public static final String SPRING_SECURITY_FORM_CODE_KEY = "code";
    public static final String SPRING_SECURITY_FORM_TYPE_KEY = "type";

    private String telParameter = SPRING_SECURITY_FORM_TEL_KEY;
    private String codeParameter = SPRING_SECURITY_FORM_CODE_KEY;
    private String typeParameter = SPRING_SECURITY_FORM_TYPE_KEY;

    private boolean postOnly = true;


    public TelValidateCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/loginByCode", "POST"));
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
}

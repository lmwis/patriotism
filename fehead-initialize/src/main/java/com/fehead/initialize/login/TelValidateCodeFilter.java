package com.fehead.initialize.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.initialize.error.SmsValidateException;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.service.TelValidateCodeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmwis on 2019-07-20 19:00
 */
public class TelValidateCodeFilter extends OncePerRequestFilter {

    private TelValidateCodeService telValidateCodeService;

    private AuthenticationFailureHandler feheadAuthenticationFailureHandler;

    private SecurityProperties securityProperties;

    private AuthenticationSuccessHandler feheadAuthenticationSuccessHandler;

    private ObjectMapper objectMapper = new ObjectMapper();

    public TelValidateCodeService getTelValidateCodeService() {
        return telValidateCodeService;
    }

    public void setTelValidateCodeService(TelValidateCodeService telValidateCodeService) {
        this.telValidateCodeService = telValidateCodeService;
    }

    public static final Logger logger = LoggerFactory.getLogger(TelValidateCodeFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String url = request.getRequestURI();

        logger.info("请求的URL："+url);

        if(StringUtils.equals(url,securityProperties.getBrowser().getSendOtpCode())){

            ServletWebRequest servletWebRequest = new ServletWebRequest(request);

            String tel =servletWebRequest.getParameter(securityProperties.getBrowser().getTelParameter());


            logger.info("请求的手机号为："+tel);

            try {
                if (telValidateCodeService.check(tel)) {
                    telValidateCodeService.send(tel);
                }
            } catch (SmsValidateException e) {
                feheadAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }

            //发送成功

            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString( CommonReturnType.creat("发送成功")));

        }


        filterChain.doFilter(request,response);
    }

    public void setFeheadAuthenticationFailureHandler(AuthenticationFailureHandler feheadAuthenticationFailureHandler) {
        this.feheadAuthenticationFailureHandler = feheadAuthenticationFailureHandler;
    }

    public void setFeheadAuthenticationSuccessHandler(AuthenticationSuccessHandler feheadAuthenticationSuccessHandler) {
        this.feheadAuthenticationSuccessHandler = feheadAuthenticationSuccessHandler;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}

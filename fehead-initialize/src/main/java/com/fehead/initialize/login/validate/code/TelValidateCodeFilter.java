package com.fehead.initialize.login.validate.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.service.TelValidateCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private ValidateFailureHandler validateFailureHandler;

    private SecurityProperties securityProperties;

    private ValidateSuccessHandler validateSuccessHandler;

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

//        if(StringUtils.equals(url,securityProperties.getBrowser().getSendOtpCode())){
//
//            ServletWebRequest servletWebRequest = new ServletWebRequest(request);
//
//            String tel =servletWebRequest.getParameter(securityProperties.getBrowser().getTelParameter());
//
//
//            logger.info("请求的手机号为："+tel);
//
//            try {
//                if (telValidateCodeService.check(tel)) {
//                    telValidateCodeService.send(tel);
//                }
//            } catch (SmsValidateException e) {
//                validateFailureHandler.onValidateFailure(request,response,e);
//                return;
//            }
//
//            //发送成功
//            validateSuccessHandler.onValidateSuccess(request,response);
//
//            return;
//
//        }



        filterChain.doFilter(request,response);
    }


    public void setValidateFailureHandler(ValidateFailureHandler validateFailureHandler) {
        this.validateFailureHandler = validateFailureHandler;
    }

    public void setValidateSuccessHandler(ValidateSuccessHandler validateSuccessHandler) {
        this.validateSuccessHandler = validateSuccessHandler;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}

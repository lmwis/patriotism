package com.fehead.initialize.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.initialize.properties.LoginType;
import com.fehead.initialize.properties.SecurityProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmwis on 2019-07-16 10:52
 */
@Component("lmwisAuthenticationSuccessHandler")
public class feheadAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(feheadAuthenticationSuccessHandler.class);

    @Autowired
    ObjectMapper objectMapper ;

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        logger.info("登陆成功");

        //如果为JSON模式，则返回JSON
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{//否则为页面模式，进行页面跳转
            super.onAuthenticationSuccess(request,response,authentication);
        }


    }
}

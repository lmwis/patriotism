package com.fehead.initialize.login.validate.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.initialize.properties.LoginType;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.response.AuthenticationReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmwis on 2019-07-24 12:30
 */
@Component
public class ValidateCodeSuccessHandler extends AbstractValidateHandler implements ValidateSuccessHandler{

    private Logger logger = LoggerFactory.getLogger(ValidateCodeFailureHandler.class);

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onValidateSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("发送成功");

        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString( AuthenticationReturnType.create("发送成功",HttpStatus.OK.value())));
        }else{
            super.onNotSupport(request,response);
        }
    }
}


package com.fehead.initialize.login.validate.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.properties.LoginType;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.response.AuthenticationReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 封装公用信息
 * @author lmwis on 2019-07-24 15:10
 */
@Component
public class AbstractValidateHandler {

    @Autowired
    protected SecurityProperties securityProperties;

    @Autowired
    protected ObjectMapper objectMapper;


    protected void onNotSupport(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        Assert.notNull(request,"request can not be null");
        Assert.notNull(request,"response can not be null");

        if(!LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            //返回不支持的信息
            response.getWriter().write(objectMapper
                    .writeValueAsString( AuthenticationReturnType
                            .create(EmBusinessError.ONLY_ACCEPT_APPLICATION_HEADER
                                    ,HttpStatus.INTERNAL_SERVER_ERROR.value())));
        }

    }
}

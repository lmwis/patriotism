package com.fehead.initialize.login.validate.code;

import com.fehead.initialize.error.SmsValidateException;
import com.fehead.initialize.properties.LoginType;
import com.fehead.initialize.response.AuthenticationReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 验证码校验的失败处理器
 * 只处理application/json请求
 *
 * @author lmwis on 2019-07-24 12:41
 */
@Component
public class ValidateCodeFailureHandler extends AbstractValidateHandler implements ValidateFailureHandler{

    private Logger logger = LoggerFactory.getLogger(ValidateCodeFailureHandler.class);


    @Override
    public void onValidateFailure(HttpServletRequest request,
                                  HttpServletResponse response,
                                  AuthenticationException exception) throws IOException {
        logger.info(exception.getMessage());
        logger.info("发送失败");

        SmsValidateException exp = (SmsValidateException)exception;

        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setStatus(exp.getErrorCode());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper
                    .writeValueAsString( AuthenticationReturnType
                            .create(exception.getMessage()
                                    , exp.getErrorCode())));
        }else{
            //返回不支持的信息
            super.onNotSupport(request,response);
        }
    }
}

package com.fehead.initialize.login.validate.code;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmwis on 2019-07-24 12:45
 */
public interface ValidateFailureHandler {

    public void onValidateFailure(HttpServletRequest request,
                                  HttpServletResponse response,
                                  AuthenticationException exception) throws IOException;


}

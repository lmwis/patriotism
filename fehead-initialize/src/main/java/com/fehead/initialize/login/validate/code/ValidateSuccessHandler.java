package com.fehead.initialize.login.validate.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmwis on 2019-07-24 12:45
 */
public interface ValidateSuccessHandler {

    public void onValidateSuccess(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException;
}

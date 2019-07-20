package com.fehead.initialize.login;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lmwis on 2019-07-20 19:42
 */

@Component("authenticationDetailsSource")
public class FeheadAuthenticationDetailsSource implements AuthenticationDetailsSource {
    @Override
    public Object buildDetails(Object o) {
        return new FeheadWebAuthenticationDetails((HttpServletRequest) o);
    }
}

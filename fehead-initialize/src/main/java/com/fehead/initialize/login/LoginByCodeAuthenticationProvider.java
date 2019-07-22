package com.fehead.initialize.login;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 *
 * 开发未完成QAQQQQQQQQQ
 * @author lmwis on 2019-07-20 20:43
 */
public class LoginByCodeAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        authentication.getPrincipal();
        authentication.getPrincipal();

        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

package com.fehead.initialize.login.tel.code.authentication;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author lmwis on 2019-07-21 18:33
 */
public interface TelUserDetailService {

    UserDetails loadUserByUserTel(String tel);

}

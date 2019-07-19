package com.fehead.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author lmwis on 2019-07-16 09:17
 */
@Component
public class MyUserDetailsServer implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(MyUserDetailsServer.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("登陆用户名："+username);
        String password = passwordEncoder.encode("123456");
        logger.info("登陆密码是："+password);
        //根据用户名查找用户信息
        return new User(username,password,
                true,true,true,
                true, AuthorityUtils
                .commaSeparatedStringToAuthorityList("admin"));
    }
}

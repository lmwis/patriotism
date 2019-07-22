package com.fehead.initialize.login;

import com.fehead.initialize.dao.UserDOMapper;
import com.fehead.initialize.dao.UserPasswordDOMapper;
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
 * @author lmwis on 2019-07-20 20:46
 */
//@Component
public class FeheadUserDetialService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("登录用户名：" + username);

//        String password = userPasswordDOMapper.selectByUserId(userDOMapper.selectByTelphone(username).getId()).getEncrptPassword();
        String password = passwordEncoder.encode("123456");
        return new User(username, password,
                true, true, true, true,
                AuthorityUtils.createAuthorityList("admin"));

    }
}

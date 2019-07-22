package com.fehead.initialize.component;

import com.fehead.initialize.dao.UserDOMapper;
import com.fehead.initialize.dao.UserPasswordDOMapper;
import com.fehead.initialize.login.TelUserDetailService;
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
 * 写代码 敲快乐
 * だからよ...止まるんじゃねぇぞ
 * ▏n
 * █▏　､⺍
 * █▏ ⺰ʷʷｨ
 * █◣▄██◣
 * ◥██████▋
 * 　◥████ █▎
 * 　　███▉ █▎
 * 　◢████◣⌠ₘ℩
 * 　　██◥█◣\≫
 * 　　██　◥█◣
 * 　　█▉　　█▊
 * 　　█▊　　█▊
 * 　　█▊　　█▋
 * 　　 █▏　　█▙
 * 　　 █
 *
 * @author Nightnessss 2019/7/16 16:19
 */
@Component
public class MyUserDetailsService implements UserDetailsService, TelUserDetailService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自定义登陆校验逻辑
     *      tel+password
     *      email+password
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        logger.info("登录用户名：" + username);

//        String password = userPasswordDOMapper.selectByUserId(userDOMapper.selectByTelphone(username).getId()).getEncrptPassword();

        String password = passwordEncoder.encode("123456");

        return new User(username, password,
                true, true, true, true,
                AuthorityUtils.createAuthorityList("admin"));
    }

    @Override
    public UserDetails loadUserByUserTel(String tel) {

        logger.info("登录手机号：" + tel);

        String password = passwordEncoder.encode("123456");

        return new User(tel, password,
                true, true, true, true,
                AuthorityUtils.createAuthorityList("admin"));

    }
}
